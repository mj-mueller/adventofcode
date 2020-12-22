package com.muellermoritz.days.day3

import com.muellermoritz.utils.logging.Logging

class Slope(private val grid: List<String>) : Logging {
    val height: Int = grid.size
    val width: Int

    init {
        if (height == 0)
            width = 0
        else {
            width = grid[0].length
            grid.forEachIndexed { index, rowWidth ->
                require(rowWidth.length == width) {
                    "Row length is not uniform. Row $index is ${grid[index].length} characters long while row 0 is ${grid[0].length}."
                }
            }
        }
        log.info("Grid (width:$width, height:$height) created.")
        log.debug(this)
    }


    fun isTree(rowIndex: Int, columnIndex: Int): Boolean {
        return grid[rowIndex][columnIndex] == TREE
    }

    fun isOpenSquare(rowIndex: Int, columnIndex: Int): Boolean {
        return grid[rowIndex][columnIndex] == OPEN_SQUARE
    }

    fun treesOnSlideTrack(horizontalMovement: Int, verticalMovement: Int): Int {
        var treesHit = if (isTree(0, 0)) 1 else 0
        var rowIndex = 0
        var columnIndex = 0

        log.trace("Sliding track (horizontal: $horizontalMovement, vertical: $verticalMovement): $this")
        while (rowIndex < this.height - 1) {
            columnIndex = (columnIndex + horizontalMovement) % this.width
            rowIndex += verticalMovement
            check(rowIndex < this.height) {
                "New rowIndex=$rowIndex is bigger than the $height of the grid."
            }
            check(columnIndex < this.width) {
                "New columnIndex=$columnIndex is bigger than the $width of the grid."
            }
            log.trace("Slope sliding position (rowIndex:$rowIndex, columnIndex:$columnIndex).")
            if (isTree(rowIndex, columnIndex)) {
                ++treesHit
                log.trace("Found tree at rowIndex:$rowIndex columnIndex:$columnIndex. $treesHit trees so far!")
            }
        }

        return treesHit
    }

    override fun toString(): String {
        var result = "\n"
        for (row in grid) {
            for (column in row) {
                result += column
            }
            result += "\n"
        }
        return result
    }

    companion object {
        const val TREE: Char = '#'
        const val OPEN_SQUARE: Char = '.'
    }
}