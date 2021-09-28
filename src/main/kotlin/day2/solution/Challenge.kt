package day2.solution

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

class Challenge {
    private fun isValidPart1(passwordWithPolicy: PasswordWithPolicy) =
        passwordWithPolicy.password.count { it == passwordWithPolicy.letter } in passwordWithPolicy.range

    @Test
    fun part1() {
        val countValidPasswords = File("src/main/kotlin/day2/input.txt")
            .readLines()
            .map { it.toPasswordPolicy() }
            .count { isValidPart1(it) }

        Assertions.assertEquals(622, countValidPasswords)
    }

    private fun isValidPart2(passwordWithPolicy: PasswordWithPolicy): Boolean {
        fun isValid(rangeIndex: Int) = passwordWithPolicy.password[rangeIndex - 1] == passwordWithPolicy.letter
        return isValid(passwordWithPolicy.range.first) xor isValid(passwordWithPolicy.range.last)
    }


    @Test
    fun part2() {
        val countValidPasswords = File("src/main/kotlin/day2/input.txt")
            .readLines()
            .map { it.toPasswordPolicy() }
            .count { isValidPart2(it) }

        Assertions.assertEquals(263, countValidPasswords)
    }
}

data class PasswordWithPolicy(
    val password: String,
    val range: IntRange,
    val letter: Char
)

fun String.toPasswordPolicy() = PasswordWithPolicy(
    password = this.substringAfter(": "),
    letter = this.substringAfter(" ").substringBefore(":").single(),
    range = this.substringBefore(" ").let {
        val (start, end) = it.split("-")
        start.toInt()..end.toInt()
    },
)