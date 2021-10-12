// Variables

fun variables() {
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
    TODO("Add 1 to x)
    assert(x == 6)
}

variables()

// function

// class

// String template

val month = "(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)"

//fun getPattern() = """\d{2}\.\d{2}\.\d{4}"""
fun getPattern(): String = TODO()

fun evaluatePattern() {
    assert(Regex(getPattern()).matches("13 JUN 1992"))
}

// Triple-quoted string


// for loop
class MyDate {

}

class DateRange(val start: MyDate, val end: MyDate)

fun iterateOverDateRange(firstDate: MyDate, secondDate: MyDate, handler: (MyDate) -> Unit) {
    for (date in firstDate..secondDate) {
        handler(date)
    }
}

// collection