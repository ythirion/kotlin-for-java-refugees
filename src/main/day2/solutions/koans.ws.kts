// Data classes
data class Person(val name: String, val age: Int)

val neo = Person("Neo", 33)
val trinity = Person("Trinity", 30)
val neoV2 = neo.copy(name = "NeoV2")

println("$neo, $trinity, $neoV2")

// Companion objects
class NonZeroPositiveInteger private constructor(private val value: Int) {
    companion object {
        fun from(value: Int): NonZeroPositiveInteger =
            if (value <= 0) throw IllegalArgumentException("NonZeroPositiveInteger can be instantiated only if value > 0")
            else NonZeroPositiveInteger(value)

        fun toInt(i: NonZeroPositiveInteger): Int = i.value
    }

    // We can declare operator as well
    operator fun plus(x: NonZeroPositiveInteger): NonZeroPositiveInteger = from(x.value + this.value)
    override fun toString(): String = value.toString()
}

NonZeroPositiveInteger.from(0)
NonZeroPositiveInteger.from(-12)
NonZeroPositiveInteger.toInt(NonZeroPositiveInteger.from(9))

// Use operator
println(NonZeroPositiveInteger.from(9) + NonZeroPositiveInteger.from(5))

// Extension Functions
data class RationalNumber(val numerator: Int, val denominator: Int)

fun Int.r(): RationalNumber = RationalNumber(this, 1)
fun Pair<Int, Int>.r(): RationalNumber = RationalNumber(first, second)

9.r()
Pair(8, 4).r()

// Higher Order Function
fun <T, R> Collection<T>.fold(
    initial: R,
    combine: (R, T) -> R
): R {
    var accumulator: R = initial
    for (element: T in this) {
        accumulator = combine(accumulator, element)
    }
    return accumulator
}