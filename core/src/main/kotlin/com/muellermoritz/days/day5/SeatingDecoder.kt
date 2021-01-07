package com.muellermoritz.days.day5

interface SeatingDecoder {
    /**
     * Resolves the given [seatCode] into a [Seat].
     */
    fun getSeat(seatCode: String): Seat
}
