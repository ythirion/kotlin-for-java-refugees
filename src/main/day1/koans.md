---
layout: default
title: Koans
parent: Day 1
grand_parent: Kotlin for java refugees
nav_order: 1
---

## Koans

### [Variables](https://kotlinlang.org/docs/basic-syntax.html#variables)

* Immutable reference : variable cannot be reassigned, but the object is not immutable
```kotlin
val a: Int = 1  // immediate assignment
val b = 2   // `Int` type is inferred
val c: Int  // Type required when no initializer is provided
c = 2
```

* Mutable reference : variable can be reassigned
```kotlin
var x = 5 // `Int` type is inferred
```

### [Function](https://play.kotlinlang.org/koans/Introduction/Hello,%20world!/Task.kt)

* Keyword `fun` for declaring a function
* Classical way :  return type at the end of the function declaration and code contains a `return` statement
```
fun classicWay(): Int {
return 1
}
```

* On the same line as the function declaration, directly return the result
```
fun lessClassic(): Int = 1
```

* Return type can be omitted
```
fun withReturnTypeInferrence() = 1
```

#### [Named arguments](https://play.kotlinlang.org/koans/Introduction/Named%20arguments/Task.kt)

* When declaring a function, you can have several parameters
```kotlin
fun reformat(
    str: String,
    normalizeCase: Boolean,
    upperCaseFirstLetter: Boolean,
    divideByCamelHumps: Boolean ,
    wordSeparator: Char,
) { /*...*/ }
```
* When calling this function, parameter name can be specified
* You can freely change the order they are listed in
```kotlin
reformat(
"String!",
false,
upperCaseFirstLetter = false,
divideByCamelHumps = true,
'_'
)
```

#### [Default arguments](https://play.kotlinlang.org/koans/Introduction/Default%20arguments/Task.kt)

* Function parameters can have default values, which are used when you skip the corresponding argument. 
* Reduces the number of overloads
* Overriding methods always use the same default parameter values as the base method
```kotlin
fun foo(
    bar: Int = 0,
    baz: Int,
) { /*...*/ }
```

```kotlin
foo(baz = 1) // The default value bar = 0 is used
```

### [String template](https://kotlinlang.org/docs/basic-syntax.html#string-templates)

### [Triple-quoted string](https://play.kotlinlang.org/koans/Introduction/Triple-quoted%20strings/Task.kt)

### [For loop](https://kotlinlang.org/docs/basic-syntax.html#for-loop)