package days

import days.day3.Slope
import days.day4.PassportReader
import days.day4.PassportVerifier
import days.day4.PassportVerifierImpl
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

internal class Day4Test{
    @Test
    fun testSample(){
        val day4 = Day4("./src/test/resources/input/day4_sample.txt")
        assertEquals(2, day4.part1())
    }

    @Test
    fun testSampleForPart2(){
        val day4 = Day4("./src/test/resources/input/day4_part2_sample.txt")

        assertEquals(4, day4.part2Sample())
    }
}