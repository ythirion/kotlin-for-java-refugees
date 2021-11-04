package solutions

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

class Challenge {

    fun multiply1(): Int {
        val lines = File("src/main/kotlin/input.txt")
            .readLines()
            .map { it.toInt() }

        for (line1 in lines) {
            for(line2 in lines) {
                if(line1 + line2 == 2020) {
                    return line1 * line2
                }
            }
        }

        return 0
    }

    fun multiply2(): Int {
        val lines = File("src/main/kotlin/input.txt")
            .readLines()
            .map { it.toInt() }

        for (line1 in lines) {
            for(line2 in lines) {
                for(line3 in lines) {
                    if(line1 + line2 + line3 == 2020) {
                        return line1 * line2 * line3
                    }
                }
            }
        }

        return 0
    }

    @Test
    fun part1() = Assertions.assertEquals(514579, multiply1())

    @Test
    fun part2() = Assertions.assertEquals(241861950, multiply2())
}