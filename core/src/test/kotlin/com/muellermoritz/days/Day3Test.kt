package com.muellermoritz.days

import com.muellermoritz.days.day3.Slope
import com.muellermoritz.days.utils.InputUtils.Companion.inputAsLines
import com.muellermoritz.utils.ResourcesRequester.Companion.getInputFileAsText
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day3Test {
    @Test
    fun testSample() {
        val day3 = Day3(getInputFileAsText("day3_sample.txt"))
        assertEquals(7, day3.part1())
    }

    @Test
    fun testPart1() {
        val day3 = Day3(getInputFileAsText("day3.txt"))

        assertEquals(237, day3.part1())
    }

    @Test
    fun testPart2() {
        val day3 = Day3(getInputFileAsText("day3.txt"))
        assertEquals(2106818610, day3.part2())
    }

    @Test
    fun testSampleForPart2() {
        val day3 = Day3(getInputFileAsText("day3_sample.txt"))
        val slope = Slope(inputAsLines(day3.text))

        assertEquals(2, slope.treesOnSlideTrack(1, 1))
        assertEquals(7, slope.treesOnSlideTrack(3, 1))
        assertEquals(3, slope.treesOnSlideTrack(5, 1))
        assertEquals(4, slope.treesOnSlideTrack(7, 1))
        assertEquals(2, slope.treesOnSlideTrack(1, 2))

        val sledges: List<Pair<Int, Int>> = listOf(Pair(1, 1), Pair(3, 1), Pair(5, 1), Pair(7, 1), Pair(1, 2))
        assertEquals(336, day3.multiplyTreeHitsForDifferentSledges(slope, sledges))
    }
}