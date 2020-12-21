package days.day4

import org.apache.logging.log4j.kotlin.Logging

class PassportReader(passportStrings: List<String>) : Logging {
    val parsePassportsRegex: Regex = """
        (?<=^|\n\n) # Lookbehind for start of string or empty line
        (.+?) # Lazy match passport key value pairs
        (?=\n\n|$) # Lookahead for end of string or empty line
        """.toRegex(
        setOf(RegexOption.DOT_MATCHES_ALL, RegexOption.COMMENTS)
    )

    val parsePassportFields: Regex = """
        (?:
        ([^\s]+):([^\s]+) # Key value pair
        )""".toRegex(setOf(RegexOption.COMMENTS))

    val passports: List<Map<String, String>>   = parsePassports(passportStrings)

    private fun parsePassports(passportStrings: List<String>): List<Map<String, String>> {
        return passportStrings.map { passportStringToMap(it) }.toList()
    }

    private fun passportStringToMap(passportString: String): Map<String, String> {
        return parsePassportFields.findAll(passportString).map { Pair(it.groupValues[1], it.groupValues[2]) }.toMap()
    }
}