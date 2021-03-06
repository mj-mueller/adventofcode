package com.muellermoritz.days

import com.muellermoritz.days.day4.PassportReader
import com.muellermoritz.days.day4.PassportVerifier
import com.muellermoritz.days.day4.PassportVerifierImpl
import com.muellermoritz.days.utils.InputUtils.Companion.emptyLineSeparatedStrings

class Day4(val text: String) : DayInterface {
    override val dayLabel="day4"

    override fun part1Impl(): Int {
        val mandatoryFields = setOf("hcl", "ecl", "pid", "eyr", "byr", "iyr", "hgt")
        val reader = PassportReader(emptyLineSeparatedStrings(text))
        val verifier: PassportVerifier = PassportVerifierImpl()
        verifier.mandatoryFields = mandatoryFields

        return reader.passports.filter { verifier.checkPassport(it) }.count()
    }

    override fun part2Impl(): Int {
        val mandatoryFields = setOf("hcl", "ecl", "pid", "eyr", "byr", "iyr", "hgt")
        val regexRules = part2Ruleset
        val reader = PassportReader(emptyLineSeparatedStrings(text))
        val verifier: PassportVerifier = PassportVerifierImpl()
        verifier.mandatoryFields = mandatoryFields
        verifier.fieldRegexRules = regexRules

        return reader.passports.filter { verifier.checkPassport(it) }.count()
    }

    fun part2Sample(): Int {
        val mandatoryFields = setOf("hcl", "ecl", "pid", "eyr", "byr", "iyr", "hgt")
        val regexRules = part2Ruleset
        val reader = PassportReader(emptyLineSeparatedStrings(text))
        val verifier: PassportVerifier = PassportVerifierImpl()
        verifier.mandatoryFields = mandatoryFields
        verifier.fieldRegexRules = regexRules

        return reader.passports.filter { verifier.checkPassport(it) }.count()
    }

    companion object {
        /*
         * Seems like matches function of Java does not match with zero length assertions.
         */
        val part2Ruleset = mapOf(
            "byr" to """^(?=19[2-9]\d$|200[0-2]$)""".toRegex(),
            "iyr" to """^(?=201\d$|2020)""".toRegex(),
            "eyr" to """^(?=202\d$|2030)""".toRegex(),
            "hgt" to """^(?=(1[5-8]\d|19[0-3])cm$)|(?=(59|6\d|7[0-6])in$)""".toRegex(),
            "hcl" to """^(?=#[0-9a-f]{6}$)""".toRegex(),
            "byr" to """^(?=19[2-9]\d|200[0-2]$)""".toRegex(),
            "ecl" to """^(?=amb|blu|brn|gry|grn|hzl|oth$)""".toRegex(),
            "pid" to """^(?=\d{9}$)""".toRegex()
        )
    }
}
