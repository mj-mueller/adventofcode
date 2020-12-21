package days

import org.apache.logging.log4j.kotlin.Logging

class Day6(filename: String) : AbstractDay(filename), Logging {

    override fun part1(): Int {
        val groups: List<String> = emptyLineSeparatedStrings()
        val answerSetPerGroup = groups.map { it.replace("\n", "").toSet() }
        return answerSetPerGroup.filter { it.isNotEmpty() }.map { it.size }.sum()
    }

    override fun part2(): Int {
        val groups: List<String> = emptyLineSeparatedStrings()
        val intersectionOfAnswersInGroup = groups.map { groupString ->
            groupString.split("\n").map { it.toSet() }.reduce { acc, value -> acc.intersect(value) }
        }
        return intersectionOfAnswersInGroup.filter { it.isNotEmpty() }.map { it.size }.sum()
    }
}