---
layout: default
title: Koans
parent: Day 1
grand_parent: Kotlin for java refugees
nav_order: 1
---

## Koans

### [Variables](https://kotlinlang.org/docs/basic-syntax.html#variables)

```
// immutable reference
val a: Int = 1  // immediate assignment
assert(a == 1)
val b = 2   // `Int` type is inferred
assert(b == 2)
val c: Int  // Type required when no initializer is provided
TODO("Assign 3 to c")
assert(c == 3)

// mutable reference
var x = 5 // `Int` type is inferred
```

### [Function](https://play.kotlinlang.org/koans/Introduction/Hello,%20world!/Task.kt)

```
fun classicWay(): Int {
return 1
}

fun lessClassic(): Int = 1

fun withReturnTypeInferrence() = 1
```

#### [Named arguments](https://play.kotlinlang.org/koans/Introduction/Named%20arguments/Task.kt)

#### [Default arguments](https://play.kotlinlang.org/koans/Introduction/Default%20arguments/Task.kt)

### [String template](https://kotlinlang.org/docs/basic-syntax.html#string-templates)

### [Triple-quoted string](https://play.kotlinlang.org/koans/Introduction/Triple-quoted%20strings/Task.kt)

### [For loop](https://kotlinlang.org/docs/basic-syntax.html#for-loop)