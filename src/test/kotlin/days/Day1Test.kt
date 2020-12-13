package days

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

internal class Day1Test {
    @Test
    fun testSample() {
        val day1 = Day1("./src/test/resources/input/day1_sample.txt")
        assertEquals(514579, day1.part1())
    }
}