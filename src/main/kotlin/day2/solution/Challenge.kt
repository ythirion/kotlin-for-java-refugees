package day2.solution

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class Challenge {
    data class PasswordWithPolicy(
        val password: String,
        val range: IntRange,
        val letter: Char
    )

    private val regex = Regex("""(\d+)-(\d+) ([a-z]): ([a-z]+)""")
    private fun String.toPasswordPolicy() =
        regex.matchEntire(this)!!
            .destructured
            .let { (start, end, letter, password) ->
                PasswordWithPolicy(password, start.toInt()..end.toInt(), letter.single())
            }

    private fun countValidPasswords(isValid: (PasswordWithPolicy) -> Boolean): Int {
        return File("src/main/kotlin/day2/input.txt")
            .readLines()
            .map { it.toPasswordPolicy() }
            .count { isValid(it) }
    }

    private fun isValidPart1(passwordWithPolicy: PasswordWithPolicy) =
        passwordWithPolicy.password.count { it == passwordWithPolicy.letter } in passwordWithPolicy.range

    private fun isValidPart2(passwordWithPolicy: PasswordWithPolicy): Boolean {
        fun isValid(rangeIndex: Int) = passwordWithPolicy.password[rangeIndex - 1] == passwordWithPolicy.letter
        return isValid(passwordWithPolicy.range.first) xor isValid(passwordWithPolicy.range.last)
    }

    @Test
    fun part1() = assertEquals(622, countValidPasswords { isValidPart1(it) })

    @Test
    fun part2() = assertEquals(263, countValidPasswords { isValidPart2(it) })
}