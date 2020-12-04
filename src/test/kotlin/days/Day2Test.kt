package days

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

internal class Day2Test{
    @Test
    fun testSample(){
            val day2 = Day2(File("./src/main/resources/days/day2_sample.txt").readLines())
            assertEquals(2, day2.part1())
    }

    @Test
    fun testPart1() {
        val day2 = Day2(File("./src/main/resources/days/day2.txt").readLines())
        assertEquals(564, day2.part1())
    }

    @Test
    fun testPart2() {
        val day2 = Day2(File("./src/main/resources/days/day2.txt").readLines())
        assertEquals(325, day2.part2())
    }
}