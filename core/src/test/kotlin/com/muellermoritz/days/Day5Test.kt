package com.muellermoritz.days

import com.muellermoritz.utils.ResourcesRequester.Companion.getInputFileAsText
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day5Test {
    @Test
    fun testSample() {
        val day5 = Day5(getInputFileAsText("day5_sample.txt"))
        assertEquals(357, day5.part1())
    }

    @Test
    fun testPart1() {
        val day5 = Day5(getInputFileAsText("day5.txt"))
        assertEquals(926, day5.part1())
    }

    @Test
    fun testpart2() {
        val day5 = Day5(getInputFileAsText("day5.txt"))
        assertEquals(657, day5.part2())
    }
}