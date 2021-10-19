---
layout: default
title: Step-by-step guide
parent: Day 1
grand_parent: Kotlin for java refugees
nav_order: 2
---

# Step-by-step guide
## Step 1 - Algorithm

```kotlin
readLines
```

* `readLines()` : open the file and return each line as String

## Step 2 - What do we need ?
* Function to run our code
* Variables to store the lines
* For loop to iterate over the lines
* If statement to test if addition equals to 2020
* String template to print the final result

[Let's learn this quickly](../koans.md)

Then go back to [main course](../day1.md)

## Step 3 - simple/naive implementation
* Read lines from the input file to a variable
```kotlin
val lines = File("src/main/kotlin/day1/input.txt")
    .readLines()
```
* Convert to `Int`
```kotlin
val lines = File("src/main/kotlin/day1/input.txt")
    .readLines().map { it.toInt() }
```
* Iterate over the lines
```kotlin
for (line1 in lines) {
    for(line2 in lines) {
        println(line1 + " " + line2);
    }
}
```
* Test if the addition of two numbers are equal
```kotlin
for (line1 in lines) {
    for(line2 in lines) {
        if(lines1 + lines2 == 2020) {
            println("OK");
            return
        }
    }
}
```
* Display the multiplication of the two previously found numbers
```kotlin
for (line1 in lines) {
    for(line2 in lines) {
        if(lines1 + lines2 == 2020) {
            println("${lines1 * lines2}");
            return
        }
    }
}
```
> Congratulations we have solved the challenge !!!

## Step 4 - Refactor / improve our code

* Store the result of the current number minus 2020 associated to the current number
```kotlin
val complements = lines.associateWith { 2020 - it }
}
```
* Find one resulting numbers in the lines

```kotlin
val pair = lines.firstNotNullOf { number ->
    val complement = complements[number]
    if(complement !=  null)
        Pair(number, complement)
    else null
}
```
* Print the first value
```kotlin
println(pair.first * pair.second)
```

Based on JetBrains work available [here](https://blog.jetbrains.com/kotlin/2021/07/advent-of-code-in-idiomatic-kotlin/)