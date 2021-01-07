package com.muellermoritz.days.utils

class InputUtils {
    companion object {
        private val emptyLineSeparatorRegex: Regex = """
        (?<=^|\n\n) # Lookbehind for start of string or empty line
        (.+?) # Lazy match passport key value pairs
        (?=\n\n|$) # Lookahead for end of string or empty line
        """.toRegex(
            setOf(RegexOption.DOT_MATCHES_ALL, RegexOption.COMMENTS)
        )

        fun inputAsLines(input: String): List<String> = input.lines()


        fun emptyLineSeparatedStrings(input: String): List<String> {
            return emptyLineSeparatorRegex.findAll(input).map { it.value }.toList()
        }

        fun inputAsIntLines(input: String): List<Int> = input.lines().map { it.toInt() }
    }
}