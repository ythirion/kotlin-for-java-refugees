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
    //foo("a"),
    //foo("b", number = 1),
    //foo("c", toUpperCase = true),
    foo(name = "d", number = 2, toUpperCase = true)
)

// Function : lambda

// Pass a lambda to the any function to check if the collection contains an even number.
// The any function gets a predicate as an argument and returns true if at least one
// element satisfies the predicate.
fun containsEven(collection: Collection<Int>): Boolean =
    collection.any(TODO())

// Variables

var x = 5 // `Int` type is inferred
// TODO("Add 1 to x")
assert(x == 6)

// for loop

val cakes = listOf("carrot", "cheese", "chocolate")

// TODO("Iterate over the list and print each element")

// conditional expression

fun summarize(value: String): String = TODO("if value length > 10, remove trailing text (after character 7) and add it a trailing '...'")

assert(summarize("this is my too long text") == "this is...")