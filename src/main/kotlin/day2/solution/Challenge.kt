package day2.solution

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

class Challenge {
    @Test
    fun exercise1() {
        val countValidPasswords = File("src/main/kotlin/day2/input.txt")
            .readLines()
            .map { it.toPasswordPolicy() }
            .count { passwordWithPolicy -> passwordWithPolicy.password.count { it == passwordWithPolicy.letter } in passwordWithPolicy.range }

        Assertions.assertEquals(622, countValidPasswords)
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