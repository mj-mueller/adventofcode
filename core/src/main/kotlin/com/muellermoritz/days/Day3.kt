package com.muellermoritz.days

import com.muellermoritz.days.day3.Slope
import com.muellermoritz.days.utils.InputUtils.Companion.inputAsLines

class Day3(val text: String) : DayInterface {
    override val dayLabel = "day3"
    private val decompose: Regex = """^([0-9]+)-([0-9]+) ([a-z]): (.*)$""".toRegex()

    override fun part1Impl(): Int {
        val horizontalMovement = 3
        val verticalMovement = 1
        val slope = Slope(inputAsLines(text))
        return slope.treesOnSlideTrack(horizontalMovement, verticalMovement)
    }

    override fun part2Impl(): Int {
        val slope = Slope(inputAsLines(text))
        return multiplyTreeHitsForDifferentSledges(
            slope,
            listOf(Pair(1, 1), Pair(3, 1), Pair(5, 1), Pair(7, 1), Pair(1, 2))
        )
    }

    fun multiplyTreeHitsForDifferentSledges(slope: Slope, sledges: List<Pair<Int, Int>>): Int {
        return sledges.map { slope.treesOnSlideTrack(it.first, it.second) }.reduce { acc, current ->
            acc * current
        }
    }
}