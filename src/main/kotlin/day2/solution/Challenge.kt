package day2.solution

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

class Challenge {
    @Test
    fun exercise1() {
        val countValidPasswords = File("src/main/kotlin/day2/input.txt")
            .readLines()
            .map { PasswordWithPolicy.parse(it) }
            .count { passwordWithPolicy -> passwordWithPolicy.password.count { it == passwordWithPolicy.letter } in passwordWithPolicy.range }

        Assertions.assertEquals(622, countValidPasswords)
    }
}

data class PasswordWithPolicy(
    val password: String,
    val range: IntRange,
    val letter: Char
) {
    companion object {
        fun parse(line: String) = PasswordWithPolicy(
            password = line.substringAfter(": "),
            letter = line.substringAfter(" ").substringBefore(":").single(),
            range = line.substringBefore(" ").let {
                val (start, end) = it.split("-")
                start.toInt()..end.toInt()
            },
        )
    }
}