---
layout: default
title: Step-by-step guide
parent: Day 2
grand_parent: Kotlin for java refugees
nav_order: 2
---

# Step-by-step guide
## Step 1 - Algorithm
```kotlin
readLines
  > parsePasswordPolicy
  > countValidPasswords
```

* `readLines()` : open the file and return each line as String
* `parsePasswordPolicy` : get a String as arg and return the corresponding data Structure
    * We need to represent the password with its associated policy
* `countValidPasswords` : function implementing the validation logic of the password based on the couple : password /
  policy

## Step 2 - What do we need ?
* Class / Data Class to represent the data structure
* Companion object
* Extension functions
* Scope functions

[Let's learn this quickly](../koans.md)

Then go back to [main course](../day2.md)

## Step 3 - simple/naive implementation
* Read lines from the input file
```kotlin
File("src/main/kotlin/day2/input.txt")
    .readLines()
```

* Represent the data structure
```kotlin
data class PasswordWithPolicy(
    val password: String,
    // Use a range or a Pair
    val range: IntRange,
    val letter: Char
)
```

* Parse the password policy from String
```kotlin
fun parse(line: String) = PasswordWithPolicy(
    password = line.substringAfter(": "),
    letter = line.substringAfter(" ").substringBefore(":").single(),
    range = line.substringBefore(" ").let {
        val (start, end) = it.split("-")
        //instantiate a range
        start.toInt()..end.toInt()
    },
)
```

* Where do we need to put the parsing logic ?
  * Companion objects

```kotlin
data class PasswordWithPolicy(
    val password: String,
    val range: IntRange,
    val letter: Char
) {
  companion object {
    fun parse(line: String) = PasswordWithPolicy(
      password = line.substringAfter(": "),
      letter = line.substringAfter(" ").substringBefore(":").single(),
      range = line.substringBefore(" ").let {
        val (start, end) = it.split("-")
        start.toInt()..end.toInt()
      },
    )
  }
}
```
* Or Extension functions
    * separate more clearly data from behaviors (functions)

```kotlin
data class PasswordWithPolicy(
    val password: String,
    val range: IntRange,
    val letter: Char
)

fun String.toPasswordPolicy() = PasswordWithPolicy(
    password = this.substringAfter(": "),
    letter = this.substringAfter(" ").substringBefore(":").single(),
    range = this.substringBefore(" ").let {
        val (start, end) = it.split("-")
        start.toInt()..end.toInt()
    },
)
```

### Validate the password
#### Part 1
* Write a function to check if a password is valid :
    * We count the number of occurrence of the given letter in the password
    * Then we check if this count is in the Policy range

```kotlin
private fun isValidPart1(passwordWithPolicy: PasswordWithPolicy) =
    // equivalent to : range.first <= x && x <= range.last
    passwordWithPolicy.password.count { it == passwordWithPolicy.letter } in passwordWithPolicy.range
```

#### Part 2
* We need to check that exactly one of the positions (stored in the range) contains the given letter
* We can use the boolean xor operator for that, which returns true if the operands are different

```kotlin
private fun isValidPart2(passwordWithPolicy: PasswordWithPolicy): Boolean {
    return (passwordWithPolicy.password[passwordWithPolicy.range.first - 1] == passwordWithPolicy.letter) xor
            (passwordWithPolicy.password[passwordWithPolicy.range.last - 1] == passwordWithPolicy.letter)
}
```

* We still have some redundancy here
* Let's factorize by using an inner function

```kotlin
private fun isValidPart2(passwordWithPolicy: PasswordWithPolicy): Boolean {
    fun isValid(rangeIndex: Int) = passwordWithPolicy.password[rangeIndex - 1] == passwordWithPolicy.letter
    return isValid(passwordWithPolicy.range.first) xor isValid(passwordWithPolicy.range.last)
}
```

### Putting all together
```kotlin
class Challenge {
    private fun isValidPart1(passwordWithPolicy: PasswordWithPolicy) =
        passwordWithPolicy.password.count { it == passwordWithPolicy.letter } in passwordWithPolicy.range

    @Test
    fun part1() {
        val countValidPasswords = File("src/main/kotlin/day2/input.txt")
            .readLines()
            .map { it.toPasswordPolicy() }
            .count { isValidPart1(it) }

        Assertions.assertEquals(622, countValidPasswords)
    }

    private fun isValidPart2(passwordWithPolicy: PasswordWithPolicy): Boolean {
        fun isValid(rangeIndex: Int) = passwordWithPolicy.password[rangeIndex - 1] == passwordWithPolicy.letter
        return isValid(passwordWithPolicy.range.first) xor isValid(passwordWithPolicy.range.last)
    }


    @Test
    fun part2() {
        val countValidPasswords = File("src/main/kotlin/day2/input.txt")
            .readLines()
            .map { it.toPasswordPolicy() }
            .count { isValidPart2(it) }

        Assertions.assertEquals(263, countValidPasswords)
    }
}
```

> Congratulations we have solved the challenge !!!

## Step 4 - Refactor / improve our code
```kotlin
class Challenge {
    private fun isValidPart1(passwordWithPolicy: PasswordWithPolicy) =
        passwordWithPolicy.password.count { it == passwordWithPolicy.letter } in passwordWithPolicy.range

    @Test
    fun part1() {
        // Hardcoded path + repeated
        val countValidPasswords = File("src/main/kotlin/day2/input.txt")
            .readLines()
            .map { it.toPasswordPolicy() }
            .count { isValidPart1(it) }

        Assertions.assertEquals(622, countValidPasswords)
    }

    private fun isValidPart2(passwordWithPolicy: PasswordWithPolicy): Boolean {
        fun isValid(rangeIndex: Int) = passwordWithPolicy.password[rangeIndex - 1] == passwordWithPolicy.letter
        return isValid(passwordWithPolicy.range.first) xor isValid(passwordWithPolicy.range.last)
    }


    @Test
    fun part2() {
        // Same pipeline than part 1 (only the count function changed)
        val countValidPasswords = File("src/main/kotlin/day2/input.txt")
            .readLines()
            .map { it.toPasswordPolicy() }
            .count { isValidPart2(it) }

        Assertions.assertEquals(263, countValidPasswords)
    }
}

data class PasswordWithPolicy(
    val password: String,
    val range: IntRange,
    val letter: Char
)

fun String.toPasswordPolicy() = PasswordWithPolicy(
    // Not really sexy
    // Use a regex instead ?
    password = this.substringAfter(": "),
    letter = this.substringAfter(" ").substringBefore(":").single(),
    range = this.substringBefore(" ").let {
        val (start, end) = it.split("-")
        start.toInt()..end.toInt()
    },
)
```

* Use a regex to parse the `PasswordPolicy`
* We assume that every input is valid

```kotlin
private val regex = Regex("""(\d+)-(\d+) ([a-z]): ([a-z]+)""")
fun String.toPasswordPolicy() =
    regex.matchEntire(this)!!
        .destructured
        .let { (start, end, letter, password) ->
            PasswordWithPolicy(password, start.toInt()..end.toInt(), letter.single())
        }
```

* `!!` is the `not-null assertion operator`
    * Converts any value to a non-null type and throws an exception if the value is null
    * More info [here](https://kotlinlang.org/docs/null-safety.html#the-operator)
* The destructured property provides components for a destructuring assignment for groups defined in the regular
  expression
* We use its result together with let and destruct it inside the lambda expression, defining start, end, letter, and
  password as parameters

* Remove duplication by using a Higher Order Function taking the validation function as parameter
    * [Higher-Order function](../koans.md)

```kotlin
private fun countValidPasswords(isValid: (PasswordWithPolicy) -> Boolean): Int {
    return File("src/main/kotlin/day2/input.txt")
        .readLines()
        .map { it.toPasswordPolicy() }
        .count { isValid(it) }
}
```

Based on JetBrains work available [here](https://blog.jetbrains.com/kotlin/2021/07/advent-of-code-in-idiomatic-kotlin-day2/)