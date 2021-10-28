---
layout: default 
title: Step-by-step guide 
parent: Day 4 
grand_parent: Kotlin for java refugees 
nav_order: 2
---

# Step-by-step guide

## Create a simple test

```kotlin
class DemoTest {
    @Test
    void `Test some rules`
    {
        assertThat("hello" + "test")
    }
}
```

## Create integration test

### Add dependencies

* Start spring boot test like in Java

 ```xml
     <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-test</artifactId>
         <scope>test</scope>
     </dependency>
 ```

### Testing controller

* Solution N°1: Create test as we do with java and Mockito

```kotlin
@MockBean
private lateinit var service: MessageService

@Test
fun `find messages`() {

    Mockito.`when`(service.findMessages()).thenReturn(listOf(Message("1", "First"), Message("2", "Second")));

    mockMvc.perform(MockMvcRequestBuilders.get("/"))
        .andExpect(MockMvcResultMatchers.status().isOk)
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("\$.[0].id").value("1"))
        .andExpect(MockMvcResultMatchers.jsonPath("\$.[0].text").value("First"))
        .andExpect(MockMvcResultMatchers.jsonPath("\$.[1].id").value("2"))
        .andExpect(MockMvcResultMatchers.jsonPath("\$.[1].text").value("Second"))

    Mockito.verify(service).findMessages()
}
```

* Solution N°2
    * Recreate the test using extensions (DSL) provider by spring
    * use [Mockk](https://mockk.io/) is a mocking library for kotlin

```xml
<dependency>
    <groupId>io.mockk</groupId>
    <artifactId>mockk</artifactId>
    <version>1.10.4</version>
</dependency>
<dependency>
    <groupId>com.ninja-squad</groupId>
    <artifactId>springmockk</artifactId>
    <version>3.0.1</version>
</dependency>
```

```kotlin
@Test
fun `find messages`() {
    every { service.findMessages() } returns listOf(
        Message("1", "First"),
        Message("2", "Second")
    )

    mockMvc.get("/")
        .andExpect { status { isOk() } }
        .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
        .andExpect {
            jsonPath("\$.[0].id", "") { value("1") }
            jsonPath("\$.[0].text") { value("First") }
            jsonPath("\$.[1].id") { value("2") }
            jsonPath("\$.[1].text") { value("Second") }
        }

    verify(exactly = 1) { service.findMessages() }
}
```

* Create a customer extention for test

### Refactoring  and clean Tests

#### Analyse tests:

* Have a business oriented name for the test
* What is a Right ? What is a valid comment ?

```kotlin
fun `returns a Right for valid comment`() {
    val blogService = BlogService()
    val article = Article(
        "Lorem Ipsum",
        "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore"
    )
    val result = blogService.addComment(article, "Amazing article !!!", "Pablo Escobar")

    assert(result.isRight)
}

```

* 1 test to test each comment value...
* 4 tests instead of 1 to maintain
* Tests should be behavior focus -> not data driven

```kotlin
fun `add a comment with the given text`() {
    val blogService = BlogService()
    val article = Article(
        "Lorem Ipsum",
        "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore"
    )
    val text = "Amazing article !!!"
    val result = blogService.addComment(article, text, "Pablo Escobar")

    assert(result.get().comments.first().text == text)
}

fun `add a comment with the given author`() {
    val blogService = BlogService()
    val article = Article(
        "Lorem Ipsum",
        "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore"
    )
    val author = "Pablo Escobar"
    val result = blogService.addComment(article, "Amazing article !!!", author)

    assert(result.get().comments.first().author == author)
}

```

* Missing assertions

```kotlin
fun `add a comment with the date of the day`() {
    val blogService = BlogService()
    val article = Article(
        "Lorem Ipsum",
        "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore"
    )
    val author = "Pablo Escobar"
    val result = blogService.addComment(article, "Amazing article !!!", author)
}
```

* What is inside the Left ?

```kotlin
fun `returns a Left when adding existing comment`() {
    val blogService = BlogService()
    val article = Article(
        "Lorem Ipsum",
        "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore",
        listOf(Comment("Amazing article !!!", "Pablo Escobar", LocalDate.now()))
    )
    val result = blogService
        .addComment(article, "Amazing article !!!", "Pablo Escobar")

    assert(result.isLeft)
}
```
