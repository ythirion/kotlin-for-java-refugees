// Variables

var x = 5
x +=1
assert(x == 6)

// function

fun start(): String = "OK"

assert(start() == "OK")

// Function : named arguments

fun joinOptions(options: Collection<String>) =
    options.joinToString(prefix = "[", postfix = "]")

// Function : default arguments

fun foo(name: String, number: Int = 42, toUpperCase: Boolean = false) =
    (if (toUpperCase) name.toUpperCase() else name) + number

fun useFoo() = listOf(
    foo("a"),
    foo("b", number = 1),
    foo("c", toUpperCase = true),
    foo(name = "d", number = 2, toUpperCase = true)
)

// string template

val month = "(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)"

//fun getPattern() = """\d{2}\.\d{2}\.\d{4}"""
fun getPattern(): String = """\d{2} ${month}\ \d{4}"""

fun evaluatePattern() {
    assert(Regex(getPattern()).matches("13 JUN 1992"))
}

// Triple-quoted string

val question = "life, the universe, and everything"
val answer = 42

val tripleQuotedString = """
    #question = "$question"
    #answer = $answer""".trimMargin("#")

fun printQuoted() {
    println(tripleQuotedString)
}

printQuoted()

// for loop

// collection