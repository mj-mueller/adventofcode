package com.muellermoritz.days

import com.muellermoritz.utils.ResourcesRequester
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

    @Test
    fun testPart1() {
        val day2 = Day2(
            ResourcesRequester.DAY_INPUT_PATH + "day2.txt"
        )
        assertEquals(564, day2.part1())
    }

    @Test
    fun testPart2() {
        val day2 = Day2(
            ResourcesRequester.DAY_INPUT_PATH + "day2.txt"
        )
        assertEquals(325, day2.part2())
    }
}