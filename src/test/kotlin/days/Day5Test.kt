package days

import days.utils.ResourcesRequester
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day5Test {
    @Test
    fun testSample() {
        val day5 = Day5(ResourcesRequester.DAY_INPUT_PATH + "day5_sample.txt")
        assertEquals(357, day5.part1())
    }
}