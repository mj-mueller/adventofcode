package days

import days.utils.ResourcesRequester
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day2Test {
    @Test
    fun testSample() {
        val day2 = Day2(
            ResourcesRequester.DAY_INPUT_PATH + "day2_sample.txt"
        )
        assertEquals(2, day2.part1())
    }
}