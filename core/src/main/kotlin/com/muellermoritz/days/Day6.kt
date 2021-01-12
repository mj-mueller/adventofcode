package com.muellermoritz.days

import com.muellermoritz.days.utils.InputUtils.Companion.emptyLineSeparatedStrings

class Day6(val text: String) : DayInterface {
    override val dayLabel="day6"

    override fun part1Impl(): Int {
        val groups: List<String> = emptyLineSeparatedStrings(text)
        val answerSetPerGroup = groups.map { it.replace("\n", "").toSet() }
        return answerSetPerGroup.filter { it.isNotEmpty() }.map { it.size }.sum()
    }

    override fun part2Impl(): Int {
        val groups: List<String> = emptyLineSeparatedStrings(text)
        val intersectionOfAnswersInGroup = groups.map { groupString ->
            groupString.split("\n").map { it.toSet() }.reduce { acc, value -> acc.intersect(value) }
        }
        return intersectionOfAnswersInGroup.filter { it.isNotEmpty() }.map { it.size }.sum()
    }
}