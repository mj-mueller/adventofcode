package com.muellermoritz.days

import com.muellermoritz.days.day5.Seat
import com.muellermoritz.days.day5.SeatingDecoder
import com.muellermoritz.days.day5.SeatingDecoderImpl
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
class Day5(filename: String) : AbstractDay(filename) {

    override fun part1(): Int {
        val decoder: SeatingDecoder = SeatingDecoderImpl()
        return inputAsLines().map { decoder.getSeat(it).id }.maxOrNull()
            ?: throw IllegalStateException("Something went wrong..")
    }


    override fun part2(): Int {
        val decoder: SeatingDecoder = SeatingDecoderImpl()
        val rowMap: Map<Int, List<Seat>> = inputAsLines().map { decoder.getSeat(it) }.groupBy { it.row }.toSortedMap()
        logger.debug("Map of seats:$rowMap")
        return findFirstMissingSeatFromCenter2(rowMap, 0)!!.id
    }

    private fun findFirstMissingSeatFromCenter2(seatRows: Map<Int, List<Seat>>, offset: Int): Seat? {
        val centerRow = SeatingDecoderImpl.MAXIMUM_ROW / 2 + 1

        if (centerRow + offset > SeatingDecoderImpl.MAXIMUM_ROW || centerRow - offset < SeatingDecoderImpl.MINIMUM_ROW) {
            return null
        }
        // Check row before. Better null checking needed..
        val rowBefore = getMissingSeatColumnInRow(seatRows[centerRow - offset]!!)
        // Check row before. Better null checking needed..
        val rowAfter = getMissingSeatColumnInRow(seatRows[centerRow + offset]!!)

        if (rowBefore != null)
            return Seat(centerRow - offset, rowBefore, SeatingDecoderImpl.part1SeatIDFunction)
        if (rowAfter != null)
            return Seat(centerRow + offset, rowAfter, SeatingDecoderImpl.part1SeatIDFunction)

        return findFirstMissingSeatFromCenter2(seatRows, offset + 1)
    }

    private fun getMissingSeatColumnInRow(seatRow: List<Seat>): Int? {
        return if (seatRow.size == SeatingDecoderImpl.MAXIMUM_COLUMN + 1) {
            null
        } else {
            ((0..7) - seatRow.map { it.column }).first()
        }
    }
}
