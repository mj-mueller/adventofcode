package com.muellermoritz.days.day5

class Seat(val row: Int, val column: Int, rowColToIDFunc: (Int, Int) -> Int) {
    val id: Int = rowColToIDFunc(row, column)

    override fun toString(): String = "Seat($row:$column,$id)"
}
