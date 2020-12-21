package days

import days.utils.ResourcesRequester
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day6Test {
    @Test
    fun testSample() {
        val day6 = Day6(ResourcesRequester.DAY_INPUT_PATH + "day6_sample.txt")
        assertEquals(11, day6.part1())
    }

    @Test
    fun testPart1() {
        val day6 = Day6(ResourcesRequester.DAY_INPUT_PATH + "day6.txt")
        assertEquals(6930, day6.part1())
    }

    @Test
    fun testpart2() {
        val day6 = Day6(ResourcesRequester.DAY_INPUT_PATH + "day6.txt")
        assertEquals(3585, day6.part2())
    }
}