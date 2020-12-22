package com.muellermoritz.days.day5

import com.muellermoritz.utils.logging.Logging

class SeatingDecoderImpl : SeatingDecoder, Logging {

    override fun getSeat(seatCode: String): Seat {
        val match = validateSeatCode(seatCode)
        return Seat(getRow(match.groupValues[1]), getColumn(match.groupValues[2]), part1SeatIDFunction)
    }

    private fun getColumn(code: String): Int =
        divideAndConquer(code, Pair(MINIMUM_COLUMN, MAXIMUM_COLUMN), columnFunction)

    private fun getRow(code: String): Int =
        divideAndConquer(code, Pair(MINIMUM_ROW, MAXIMUM_ROW), rowFunction)

    private fun divideAndConquer(
        code: String,
        minMax: Pair<Int, Int>,
        calcNewLimitsFun: (Char, Pair<Int, Int>) -> (Pair<Int, Int>)
    ): Int {
        if (code.isEmpty()) {
            if (minMax.first != minMax.second) {
                val e =
                    IllegalStateException(
                        "Traversed whole code $code and result is not distinct" +
                                " (min${minMax.first}:max${minMax.second})!"
                    )
                log.error("Error during divide and conquer algorithm", e)
            }
            return minMax.first
        } else {
            return divideAndConquer(code.substring(1), calcNewLimitsFun(code.first(), minMax), calcNewLimitsFun)
        }
    }

    private fun validateSeatCode(seatCode: String): MatchResult {
        return seatCodeRegex.find(seatCode) ?: throw IllegalArgumentException("Seat code $seatCode is invalid.")
    }

    companion object {
        // Better put this into a data class Plane next time
        const val FRONT_ROW = 'F'
        const val BACK_ROW = 'B'
        const val LEFT_COLUMN = 'L'
        const val RIGHT_COLUMN = 'R'
        const val MINIMUM_ROW = 0
        const val MAXIMUM_ROW = 127
        const val MINIMUM_COLUMN = 0
        const val MAXIMUM_COLUMN = 7

        // Needs to be companion to be used in the lambdas
        private fun Pair<Int, Int>.halfDistance(): Int {
            return (this.second - this.first + 1) / 2
        }

        val part1SeatIDFunction: (Int, Int) -> Int = { row, col -> row * 8 + col }
        val seatCodeRegex = "^([${FRONT_ROW}${BACK_ROW}]{7})([${LEFT_COLUMN}${RIGHT_COLUMN}]{3})\$".toRegex()

        val rowFunction: (Char, Pair<Int, Int>) -> (Pair<Int, Int>) = { char, minMax ->
            if (char == FRONT_ROW)
                Pair(minMax.first, minMax.second - minMax.halfDistance())
            else
                Pair(minMax.first + minMax.halfDistance(), minMax.second)
        }
        val columnFunction: (Char, Pair<Int, Int>) -> (Pair<Int, Int>) = { char, minMax ->
            if (char == LEFT_COLUMN)
                Pair(minMax.first, minMax.second - minMax.halfDistance())
            else
                Pair(minMax.first + minMax.halfDistance(), minMax.second)
        }
    }
}
