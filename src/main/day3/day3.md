---
layout: default
title: Day 3
parent: Kotlin for java refugees
nav_order: 3
has_children: true
---

# Day 3 - Spring in Kotlin
## [Challenge](https://youtu.be/gf-kjD2ZmZk) - create a SpringBoot app
* Create a SpringBoot application from scratch
  * Use Spring Initializr
  * Add "Spring Web" / "Spring Data JDBC" / "H2 Database"
  ![Spring initalizr](img/spring-init.png)
  
### Observe the project
* Observe the `pom.xml`
  * What is different from what you are used to ?
* Analyze code structure
  * How does it differ from what you are used to ?
* Open the `DemoApplication.kt` file
  * What do you notice ?

### Create a RestController
1) Return a hardcoded list of Message on `GET`
   * A message is composed by an id (String nullable) and a text
2) Add the missing Layers
  * Create a Service that will contain 2 functions : `findMessages` / `addMessage`
3) What else do we need to do ?
4) Add the Database / repository
   * Create the Repository needed by the Service to retrieve the data
5) Plug everything together
   * Use our repository in our Service
   * Finalize our Service implementation by saving the Message
   * Use our Service in our Controller
   * Add the POST mapping to be able to create new messages
   
### Configure the database
* Add an `application.properties` file
```properties
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.url=jdbc:h2:file:./data/testdb
spring.datasource.username=sa
spring.datasource.password=password
spring.datasource.schema=classpath:sql/schema.sql
spring.datasource.initialization-mode=always
```

### Add the init script
* In the `application.properties` we set the schema to `sql/schema.sql`
  * Create this file that initializes the db schema
```roomsql
CREATE TABLE IF NOT EXISTS messages
(
    id   VARCHAR(60) DEFAULT RANDOM_UUID() PRIMARY KEY,
    text VARCHAR NOT NULL
);
```

### Use our favorite mapper (MapStruct)
* In real life we would not expose our Entities directly from our Controller
  * Let's add a decoupling layer
  * Change the public contract :
    * Pass a `AddMessage` command in the POST and return a `MessageDto` in the GET
* Create our `MessageMapper` to map : 
  * AddMessage -> Message
  * Message -> MessageDto
* Use it in our `Service`
* Refactor calls to the Mapper to use extension functions to hide mapper usage to the outside world

Final code available [here](https://github.com/ythirion/kotlin-for-java-refugees-day3)
