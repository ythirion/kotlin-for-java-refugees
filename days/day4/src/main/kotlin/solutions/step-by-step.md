---
layout: default 
title: Step-by-step guide 
parent: Day 4 -  Testing
grand_parent: Kotlin for java refugees 
nav_order: 2
---

# Step-by-step guide

## [Let's learn new concepts in kotlin](../koans.md)

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

@WebMvcTest
class WebTierTests(@Autowired val mockMvc: MockMvc) {
  @MockBean
  private lateinit var service: MessageService

  @Test
  fun `find messages`() {

    Mockito.`when`(service.findMessages()).thenReturn(listOf(Message("1", "First"), Message("2", "Second")))

    mockMvc.perform(MockMvcRequestBuilders.get("/"))
      .andExpect(MockMvcResultMatchers.status().isOk)
      .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.jsonPath("\$.[0].id").value("1"))
      .andExpect(MockMvcResultMatchers.jsonPath("\$.[0].text").value("First"))
      .andExpect(MockMvcResultMatchers.jsonPath("\$.[1].id").value("2"))
      .andExpect(MockMvcResultMatchers.jsonPath("\$.[1].text").value("Second"))

    Mockito.verify(service).findMessages()
  }
}

```

* Solution N°2
    * Recreate the test using extensions (DSL) provider by spring
    * use [Mockk](https://mockk.io/) is a mocking library for kotlin supported by spring tests

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



@MockkBean
private lateinit var service: MessageService

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

* Isolate the technical glue in a `companion` and `top level function`


```
BlogTestUtils.kt 
// Top level Function 
fun articleWithComments(comments: List<Comment>): Article =
  Article(
    "Lorem Ipsum",
    "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore",
    comments
  )

fun assertAddedComment(
  article: Article,
  expectedText: String,
  expectedAuthor: String
) {
  val addedComment = article.comments.last()
  assertionJunit5.assertEquals(expectedAuthor, addedComment.author)
  assertionJ.assertThat( addedComment.creationDate).isEqualTo(LocalDate.now())
  // Equals operators
  Assertions.assertTrue(expectedText == addedComment.text)
}

// using typealias to differentiate two libs Junit and AssertJ
typealias assertionJunit5 = org.junit.jupiter.api.Assertions
typealias assertionJ = org.assertj.core.api.Assertions

```

```kotlin
class BlogServiceRefactoredTest {
  private val blogService = BlogService()

  @Nested
  inner class `add new comment`{
    @Test
    fun `in an empty Article including given text & author`() {
      val updatedArticle = blogService.addComment(emptyArticle, text, author)
      assert(updatedArticle.isRight)
      assertAddedComment(updatedArticle.get(), text, author)
    }
    @Test
    fun `in an Article containing existing comments`() {
      val newText = "Finibus Bonorum et Malorum"
      val newAuthor = "Al Capone"

      val updatedArticle = blogService.addComment(articleWith1Comment, newText, newAuthor)

      assert(updatedArticle.isRight)
      Assertions.assertEquals(updatedArticle.get().comments.size, 2)
      assertAddedComment(updatedArticle.get(), newText, newAuthor)
    }

  }
  
  @Test
  fun `return an error when adding existing comment`() {
    val updatedArticle = blogService.addComment(articleWith1Comment, text, author)
    Assertions.assertTrue(updatedArticle.isLeft)
    Assertions.assertEquals(1, updatedArticle.left.size)
    Assertions.assertEquals(BlogService.ErrorMessage.existingCommentError, updatedArticle.left.first().description)
  }

  companion object BlogRefactoredTests {
    private const val text = "Amazing article !!!"
    private const val author = "Pablo Escobar"

    private val emptyArticle: Article = articleWithComments(emptyList())
    private val articleWith1Comment: Article = articleWithComments(listOf(Comment(text, author, LocalDate.now())))

  }
}
```

### Tests integration using TestContainers

** Add Dependecy for Postgres jdbc driver

```xml
<dependency>
  <groupId>org.postgresql</groupId>
  <artifactId>postgresql</artifactId>
  <version>42.3.1</version>
</dependency>
```

** Add Tests containers dependecies

```xml
        <!-- Integration with Junit -->
<dependency>
  <groupId>org.testcontainers</groupId>
  <artifactId>junit-jupiter</artifactId>
  <version>1.16.0</version>
  <scope>test</scope>
</dependency>
        <!-- Integration with Postgre -->
<dependency>
  <groupId>org.testcontainers</groupId>
  <artifactId>postgresql</artifactId>
  <version>1.16.0</version>
  <scope>test</scope>
</dependency>

```

* Update the schema definition to use postgre database specific syntax

 ```
 CREATE TABLE IF NOT EXISTS messages (
    id varchar(50) DEFAULT uuid_generate_v4()::text,
    text VARCHAR(500),
    CONSTRAINT id_messages PRIMARY KEY (id)
 );
 ```

* Update application.properties to use PostgreSQL driver

```properties
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
```

* A simple test to start the container and verify if is up running
* To Start the container once per test class, we can delarce it in a `companion object`
* Make sure that docker is running in this machine

```kotlin
@Testcontainers
class IntegrationTests {
  companion object {
    @Container
    val container = postgres("13-alpine") {
      withDatabaseName("db")
      withUsername("user")
      withPassword("password")
      withInitScript("sql/schema.sql")
    }
  }

  @Test
  fun `container is up and running`() {
    Assertions.assertTrue(container.isRunning)
  }
}
```

* Create an integration test for Repository 

```kotlin
@Testcontainers
class MessageRepositoryTest(@Autowired  var messageRepository: MessageRepository) {
    val message = Message("1", "My first Message")

    @BeforeEach
    fun cleanup() {
        messageRepository.deleteAll();
    }

    companion object {
        @Container
        val postgreDBContainer = PostgreSQLContainer(DockerImageName.parse("postgres:13-alpine"))
            .withDatabaseName("db")
            .withUsername("user")
            .withPassword("password")
            .withInitScript("sql/schema.sql")

        @JvmStatic
        @DynamicPropertySource
        fun datasourceConfig(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", postgreDBContainer::getJdbcUrl)
            registry.add("spring.datasource.password", postgreDBContainer::getPassword)
            registry.add("spring.datasource.username", postgreDBContainer::getUsername)
        }
    }

    @Test
    fun `add a new message`() {
        val id = "${Random.nextInt()}".uuid()
        val message = Message(id, "some message")
        assertDoesNotThrow { messageRepository.save(message) }
        val result = messageRepository.findById(id).get();
        Assertions.assertEquals(message.id, result.id)
        Assertions.assertEquals(message.text, result.text)
    }

}
```