package com.muellermoritz.days

import com.muellermoritz.days.utils.InputUtils.Companion.inputAsIntLines

class Day1(val text: String) : DayInterface {
    override val dayLabel: String = "day1"

    override fun part1Impl(): Int {
        val lines = inputAsIntLines(text)
        for (number1 in lines)
            for (number2 in lines)
                if (number1 + number2 == 2020)
                    return number1 * number2
        throw IllegalArgumentException("No combination of two numbers sums up to 2020.")
    }

    override fun part2Impl(): Int {
        val lines = inputAsIntLines(text)
        for (number1 in lines)
            for (number2 in lines)
                for (number3 in lines)
                    if (number1 + number2 + number3 == 2020)
                        return number1 * number2 * number3
        throw IllegalArgumentException("No combination of three numbers sums up to 2020.")
    }
}