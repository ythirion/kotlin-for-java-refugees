// Variables

var x = 5 // `Int` type is inferred
TODO("Add 1 to x")
assert(x == 6)

// Function

fun start(): String = TODO()

assert(start() == "OK")

// Function : named arguments

fun joinOptions(options: Collection<String>) =
    options.joinToString(TODO())

// Function : default arguments

fun foo(name: String, number: Int, toUpperCase: Boolean) =
    (if (toUpperCase) name.toUpperCase() else name) + number

fun useFoo() = listOf(
    foo("a"),
    foo("b", number = 1),
    foo("c", toUpperCase = true),
    foo(name = "d", number = 2, toUpperCase = true)
)

// String template

val month = "(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)"

//fun getPattern() = """\d{2}\.\d{2}\.\d{4}"""
fun getPattern(): String = TODO()

fun evaluatePattern() {
    assert(Regex(getPattern()).matches("13 JUN 1992"))
}

// Triple-quoted string

val question = "life, the universe, and everything"
val answer = 42

TODO("Replace the trimIndent call with the trimMargin call taking # as the prefix value so that the resulting string doesn't contain the prefix character.")
val tripleQuotedString = """
    #question = "$question"
    #answer = $answer""".trimIndent()

fun printQuoted() {
    println(tripleQuotedString)
}

printQuoted()

// for loop

/*class MyDate {

}

class DateRange(val start: MyDate, val end: MyDate)

fun iterateOverDateRange(firstDate: MyDate, secondDate: MyDate, handler: (MyDate) -> Unit) {
    for (date in firstDate..secondDate) {
        handler(date)
    }
}*/

// collection