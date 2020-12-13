package days

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

internal class Day3Test{
    @Test
    fun testSample(){
        val day3 = Day3("./src/main/resources/days/day3_sample.txt")
        assertEquals(7, day3.part1())
    }

    @Test
    fun testStepFunction(){
        val day3 = Day3("./src/main/resources/days/day3_sample.txt")
        assertEquals(0, day3.calulatePos(0){ 4})
        assertEquals(1, day3.calulatePos(1) { it + 1})
        assertEquals(3, day3.calulatePos(3) { it + 1})
        assertEquals(1, day3.calulatePos(12) { it + 1})
        assertEquals(11, day3.calulatePos(6) { it + 2})
        assertEquals(3, day3.calulatePos(3) { it + 5})
        assertEquals(12, day3.calulatePos(5) { it + 7})
    }
}