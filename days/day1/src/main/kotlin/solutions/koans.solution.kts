// function

fun start(): String = "OK"

assert(start() == "OK")

// Function : named arguments

fun joinOptions(options: Collection<String>) =
    options.joinToString(prefix = "[", postfix = "]")

// Function : default arguments

fun foo(name: String, number: Int = 42, toUpperCase: Boolean = false) =
    (if (toUpperCase) name.uppercase() else name) + number

fun useFoo() = listOf(
    foo("a"),
    foo("b", number = 1),
    foo("c", toUpperCase = true),
    foo(name = "d", number = 2, toUpperCase = true)
)

// Function : lambda

// Pass a lambda to the any function to check if the collection contains an even number.
// The any function gets a predicate as an argument and returns true if at least one
// element satisfies the predicate.
fun containsEven(collection: Collection<Int>): Boolean =
    collection.any { it % 2 == 0 }

// Variables

var x = 5
x +=1
assert(x == 6)

// for loop

val cakes = listOf("carrot", "cheese", "chocolate")

for (cake in cakes) {
    println(cake)
}

// conditional expression

fun summarize(value: String): String = if (value.length > 10) value.substring(0..7) + "..." else value

assert(summarize("this is my too long text") == "this is...")

