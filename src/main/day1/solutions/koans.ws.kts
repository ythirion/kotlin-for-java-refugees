// Variables

fun variables() {
    // immutable reference
    val a: Int = 1  // immediate assignment
    assert(a == 1)
    val b = 2   // `Int` type is inferred
    assert(b == 2)
    val c: Int  // Type required when no initializer is provided
    c = 3
    assert(c == 3)

    // mutable reference
    var x = 5 // `Int` type is inferred
    x +=1
    assert(x == 6)
}

variables()

// function

// class

// string template

val month = "(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)"

//fun getPattern() = """\d{2}\.\d{2}\.\d{4}"""
fun getPattern(): String = """\d{2} ${month}\ \d{4}"""

fun evaluatePattern() {
    assert(Regex(getPattern()).matches("13 JUN 1992"))
}

// for loop

// collection