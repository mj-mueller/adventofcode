package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day2Test {
    @Test
    fun testSample() {
        val day2 = Day2("./src/test/resources/input/day2_sample.txt")
        assertEquals(2, day2.part1())
    }
}