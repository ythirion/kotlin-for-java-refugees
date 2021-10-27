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

// Function : lambda

// for loop

// conditional expression
