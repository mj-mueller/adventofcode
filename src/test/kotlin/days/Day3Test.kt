package days

import days.day3.Slope
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

internal class Day3Test{
    @Test
    fun testSample(){
        val day3 = Day3("./src/test/resources/input/day3_sample.txt")
        assertEquals(7, day3.part1())
    }

    @Test
    fun testSampleForPart2(){
        val day3 = Day3("./src/test/resources/input/day3_sample.txt")
        val slope= Slope(day3.inputAsLines())

        assertEquals(2, slope.treesOnSlideTrack(1,1))
        assertEquals(7, slope.treesOnSlideTrack(3,1))
        assertEquals(3, slope.treesOnSlideTrack(5,1))
        assertEquals(4, slope.treesOnSlideTrack(7,1))
        assertEquals(2, slope.treesOnSlideTrack(1,2))

        val sledges: List<Pair<Int, Int>> = listOf(Pair(1,1), Pair(3,1), Pair(5,1), Pair(7,1), Pair(1,2))
        assertEquals(336, day3.multiplyTreeHitsForDifferentSledges(slope, sledges))
    }
}