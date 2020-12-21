package days

import days.utils.ResourcesRequester
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day4Test {
    @Test
    fun testSample() {
        val day4 = Day4(ResourcesRequester.DAY_INPUT_PATH + "day4_sample.txt")
        assertEquals(2, day4.part1())
    }

    @Test
    fun testPart1() {
        val day4 = Day4(ResourcesRequester.DAY_INPUT_PATH + "day4.txt")
        assertEquals(208, day4.part1())
    }

    @Test
    fun testSampleForPart2() {
        val day4 = Day4(ResourcesRequester.DAY_INPUT_PATH + "day4_part2_sample.txt")

        assertEquals(4, day4.part2Sample())
    }

    @Test
    fun testPart2() {
        val day4 = Day4(ResourcesRequester.DAY_INPUT_PATH + "day4.txt")
        assertEquals(167, day4.part2())
    }
}