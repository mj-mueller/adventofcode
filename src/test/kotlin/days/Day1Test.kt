package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day1Test {
    @Test
    fun testSample() {
        val day1 = Day1("./src/test/resources/input/day1_sample.txt")
        assertEquals(514579, day1.part1())
    }
}