package com.muellermoritz.days

import com.muellermoritz.utils.ResourcesRequester
import com.muellermoritz.utils.logging.Logging


abstract class AbstractDay(filename: String) : Logging {
    val emptyLineSeparatorRegex: Regex = """
        (?<=^|\n\n) # Lookbehind for start of string or empty line
        (.+?) # Lazy match passport key value pairs
        (?=\n\n|$) # Lookahead for end of string or empty line
        """.toRegex(
        setOf(RegexOption.DOT_MATCHES_ALL, RegexOption.COMMENTS)
    )

    val input: String = ResourcesRequester.getResource(filename).readText()

    fun inputAsLines(): List<String> = input.lines()

    fun emptyLineSeparatedStrings(): List<String> {
        return emptyLineSeparatorRegex.findAll(input).map { it.value }.toList()

    }

    fun inputAsIntLines(): List<Int> = input.lines().map { it.toInt() }

    fun printResults() {
        log.info("${this.javaClass.simpleName} part1: ${part1()}")
        log.info("${this.javaClass.simpleName} part2: ${part2()}")
    }

    abstract fun part1(): Int

    abstract fun part2(): Int
}
