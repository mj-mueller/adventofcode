package days

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

internal class Day1Test {
    @Test
    fun testSample() {
        val day1 = Day1("./src/main/resources/days/day1_sample.txt")
        assertEquals(514579, day1.part1())
    }

    @Test
    fun testPart1() {
        val day1 = Day1("./src/main/resources/days/day1.txt")
        assertEquals(927684, day1.part1())
    }

    @Test
    fun testPart2() {
        val day1 = Day1("./src/main/resources/days/day1.txt")
        assertEquals(292093004, day1.part2())
    }
}