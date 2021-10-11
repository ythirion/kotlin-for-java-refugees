---
layout: default
title: Day 3
parent: Kotlin for java refugees
nav_order: 3
has_children: true
---

# Day 3 - Spring in Kotlin
## [Challenge](https://youtu.be/gf-kjD2ZmZk)
* Create a SpringBoot application from scratch
  * Use Spring Initializr
  * Maven / demo project
  * Add "Spring Web" / "Spring Data JDBC" / "H2 Database"
  * Observe the pom
* Analyze code structure
  * How does it differ from what you are used to ?

### Create a message Controller (RestController)
  * Return a hardcoded list of Message
  * A message is composed by an id and a text

### Add the Database
* Create a Service that will contain 2 functions
  * `findMessages` / `addMessage`
  * Put a TODO in the addMessage function for now
  * This Service needs a Repository to retrieve the data
    * Create it
* Retrieve the Data from the Database

#### Create messages
* Add a new function in your controller to be able to create messages
  * Call your service / repository

#### Configure the database
* add an `application.properties` file
![img.png](img/db-properties.png)

#### Add the init script
```roomsql
CREATE TABLE IF NOT EXISTS messages
(
    id   VARCHAR(60) DEFAULT RANDOM_UUID() PRIMARY KEY,
    text VARCHAR NOT NULL
);
```

### Handle null id in the message
* if the message id is null : generate one based on uuid
  * How could we implement that ?
  * Extension function can be helpful to generate a UUID based on the text input

### ToDo
* Demonstrates that we have access to KT extensions
* Add Custom Validation
  * Demonstrate that is easy to add custom annotations

Final repository available [here](https://github.com/kotlin-hands-on/spring-time-in-kotlin-episode1)

## Koans
* [Spread operator](https://kotlinlang.org/docs/functions.html#variable-number-of-arguments-varargs)
  * No worries this is not a pointer 😜
```kotlin
runApplication<DemoApplication>(*args)
```
* [The TODO() function](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-t-o-d-o.html)
