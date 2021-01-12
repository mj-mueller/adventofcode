package com.muellermoritz.days

import com.muellermoritz.days.utils.InputUtils.Companion.inputAsLines

class Day2(val text: String) : DayInterface {
    override val dayLabel = "day2"


    private val decompose: Regex = """^([0-9]+)-([0-9]+) ([a-z]): (.*)$""".toRegex()

    override fun part1Impl(): Int {
        return checkLines { min, max, letter, password ->
            password.filter { it == letter }.count() in min..max
        }
    }

    override fun part2Impl(): Int {
        return checkLines { pos1, pos2, letter, password ->
            (password[pos1 - +1] == letter) xor (password[pos2 - 1] == letter)
        }
    }

    private fun checkLines(
        checkFunction: (min: Int, max: Int, letter: Char, password: String) -> Boolean
    ): Int {
        return inputAsLines(text).mapNotNull { decompose.find(it) }.map {
            checkFunction(
                it.groupValues[1].toInt(),
                it.groupValues[2].toInt(),
                it.groupValues[3].single(),
                it.groupValues[4]
            )
        }.filter { it }.count()
    }
}