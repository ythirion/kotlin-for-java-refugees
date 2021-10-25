// Data classes

// Companion objects
class NonZeroPositiveInteger private constructor(private val value: Int)

// Extension Functions
data class RationalNumber(val numerator: Int, val denominator: Int)

fun Int.r(): RationalNumber = TODO()
fun Pair<Int, Int>.r(): RationalNumber = TODO()

9.r()
Pair(8, 4).r()