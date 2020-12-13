package days.day3

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.lang.IllegalStateException

internal class SlopeTest {
    private val emptyField: List<String> = emptyList()
    private val illegalDimensionField: List<String> = listOf("..#..", "##.....#")

    @Test
    fun test_slope_emptyFieldInit_shouldBeZero() {
        val slope = Slope(emptyField)
        assertEquals(0, slope.width)
        assertEquals(0, slope.height)
    }

    @Test
    fun test_slope_normalFieldInit_checkDimension() {
        val field: List<String> = listOf(
            "..#..",
            "##..#",
            "....."
        )
        val slope = Slope(field)
        assertEquals(5, slope.width)
        assertEquals(3, slope.height)
    }

    @Test
    fun test_slope_unevenDimensionsInit_shouldThrowException() {
        assertThrows(IllegalArgumentException::class.java) {
            Slope(illegalDimensionField)
        }
    }

    @Test
    fun test_slope_checkTree() {
        val field: List<String> = listOf(
            "..",
            "##",
            "#."
        )
        val trees: List<Pair<Int, Int>> = listOf(Pair(1, 0), Pair(1, 1), Pair(2, 0))
        val openSquares: List<Pair<Int, Int>> = listOf(Pair(0, 0), Pair(0, 1), Pair(2, 1))

        val slope = Slope(field)

        trees.forEach {
            assert(slope.isTree(it.first, it.second))
        }
        openSquares.forEach {
            assertFalse(slope.isTree(it.first, it.second))
        }
    }

    @Test
    fun test_slope_checkOpenSquare() {
        val field: List<String> = listOf(
            "....#",
            "##..#",
            "#...#"
        )
        val trees: List<Pair<Int, Int>> = listOf(Pair(1, 0), Pair(1, 1), Pair(2, 0), Pair(0, 4), Pair(1, 4), Pair(2, 4))
        val openSquares: List<Pair<Int, Int>> = listOf(
            Pair(0, 0),
            Pair(0, 1),
            Pair(2, 1),
            Pair(0, 2),
            Pair(0, 3),
            Pair(1, 2),
            Pair(1, 3),
            Pair(2, 2),
            Pair(2, 3)
        )

        val slope = Slope(field)

        openSquares.forEach {
            assert(slope.isOpenSquare(it.first, it.second))
        }
        trees.forEach {
            assertFalse(slope.isOpenSquare(it.first, it.second))
        }
    }

    @Test
    fun test_slope_slide() {
        val field: List<String> = listOf(
            "##...#",
            "##.#..",
            "#.#.##",
            "#..#..",
            "#...##"
        )
        val slope = Slope(field)

        var horizontalMovement = 1
        var verticalMovement = 1

        assertEquals(5, slope.treesOnSlideTrack(horizontalMovement, verticalMovement))


        horizontalMovement = 2
        verticalMovement = 1

        assertEquals(3, slope.treesOnSlideTrack(horizontalMovement, verticalMovement))


        horizontalMovement = 1
        verticalMovement = 2

        assertEquals(1, slope.treesOnSlideTrack(horizontalMovement, verticalMovement))
    }

    @Test
    fun test_slope_slideOutOfBounds_IllegalState() {
        val field: List<String> = listOf(
            "##..",
            "##.#",
            "#.#.",
            ".#..",
            "#.##"
        )
        val slope = Slope(field)

        var horizontalMovement = 10
        var verticalMovement = 2

        assertDoesNotThrow {
            slope.treesOnSlideTrack(horizontalMovement, verticalMovement)
        }

        horizontalMovement = 2
        verticalMovement = 10
        assertThrows(IllegalStateException::class.java) {
            slope.treesOnSlideTrack(horizontalMovement, verticalMovement)
        }
    }
}