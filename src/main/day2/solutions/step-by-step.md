## Step 1 - Algorithm

```Kotlin
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

Go back to [main course](../day2.md)

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
    * Companion object

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
```

* Or Extension functions
    * `separate more clearly data from behaviors (functions)`

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

* Putting all together

```kotlin
@Test
fun exercise1() {
    val countValidPasswords = File("src/main/kotlin/day2/input.txt")
        .readLines()
        .map { it.toPasswordPolicy() }
        .count { passwordWithPolicy -> passwordWithPolicy.password.count { it == passwordWithPolicy.letter } in passwordWithPolicy.range }

    Assertions.assertEquals(622, countValidPasswords)
}
```