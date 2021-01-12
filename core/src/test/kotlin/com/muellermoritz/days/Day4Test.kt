package com.muellermoritz.days

import com.muellermoritz.utils.ResourcesRequester.Companion.getInputFileAsText
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day4Test {
    @Test
    fun testSample() {
        val day4 = Day4(getInputFileAsText("day4_sample.txt"))
        assertEquals(2, day4.part1())
    }

    @Test
    fun testPart1() {
        val day4 = Day4(getInputFileAsText("day4.txt"))
        assertEquals(208, day4.part1())
    }

    @Test
    fun testSampleForPart2() {
        val day4 = Day4(getInputFileAsText("day4_part2_sample.txt"))

        assertEquals(4, day4.part2Sample())
    }

    @Test
    fun testPart2() {
        val day4 = Day4(getInputFileAsText("day4.txt"))
        assertEquals(167, day4.part2())
    }
}