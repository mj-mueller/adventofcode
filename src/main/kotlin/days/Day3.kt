package days

import days.day3.Slope

class Day3(filename: String) : AbstractDay(filename) {
    private val decompose: Regex = """^([0-9]+)-([0-9]+) ([a-z]): (.*)$""".toRegex()

    override fun part1(): Int {
        val horizontalMovement = 3
        val verticalMovement = 1
        val slope = Slope(inputAsLines())
        return slope.treesOnSlideTrack(horizontalMovement, verticalMovement)
    }

    override fun part2(): Int {
        val slope = Slope(inputAsLines())
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