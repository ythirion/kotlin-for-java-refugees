fun <T> asList(vararg ts: T): List<T> {
    val result = ArrayList<T>()
    for (t in ts) // ts is an Array
        result.add(t)
    return result
}

val list = listOf(1, 2, 3)
// If you want to pass a primitive type array into vararg, you need to convert it to a regular (typed) array using the toTypedArray() function:
asList(*list.toTypedArray())