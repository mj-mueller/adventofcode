package com.muellermoritz.days.day4

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test

class PassportVerifierTest {

    @Test
    fun test_passportVerifier_normalPassportNoPolicy_success() {
        val passport: Map<String, String> = mapOf(
            "iyr" to "2013",
            "ecl" to "brn",
            "cid" to "324",
            "eyr" to "2024",
            "pid" to "760753108",
            "hcl" to "#ae17e1",
            "byr" to "1931",
            "hgt" to "179cm"
        )
        val passport2: Map<String, String> = emptyMap()
        val verifier: PassportVerifier = PassportVerifierImpl()

        assert(verifier.checkPassport(passport))
        assert(verifier.checkPassport(passport2))
    }

    @Test
    fun test_passportVerifier_normalPassportOneMandatoryField() {
        val passport: Map<String, String> = mapOf(
            "iyr" to "2013",
            "ecl" to "brn",
            "cid" to "324",
            "eyr" to "2024",
            "pid" to "760753108",
            "hcl" to "#ae17e1",
            "byr" to "1931",
            "hgt" to "179cm"
        )
        val passport2: Map<String, String> = mapOf(
            "iyr" to "2013",
            "hgt" to "179cm"
        )
        val passport3: Map<String, String> = mapOf(
            "iyr" to "2013",
            "ecl" to "brn",
            "eyr" to "2024",
            "pid" to "760753108",
            "hcl" to "#ae17e1",
            "byr" to "1931",
            "hgt" to "179cm"
        )
        val passport4: Map<String, String> = emptyMap()
        val verifier: PassportVerifier = PassportVerifierImpl()

        verifier.mandatoryFields = setOf("cid")

        assert(verifier.checkPassport(passport))
        assertFalse(verifier.checkPassport(passport2))
        assertFalse(verifier.checkPassport(passport3))
        assertFalse(verifier.checkPassport(passport4))
    }


    @Test
    fun test_passportVerifier_normalPassportManyMandatoryField_success() {
        val passport: Map<String, String> = mapOf(
            "iyr" to "2013",
            "ecl" to "brn",
            "cid" to "324",
            "eyr" to "2024",
            "pid" to "760753108",
            "hcl" to "#ae17e1",
            "byr" to "1931",
            "hgt" to "179cm"
        )
        val passport2: Map<String, String> = mapOf(
            "iyr" to "2013",
            "hgt" to "179cm"
        )
        val passport3: Map<String, String> = mapOf(
            "iyr" to "2013",
            "ecl" to "brn",
            "eyr" to "2024",
            "pid" to "760753108",
            "hcl" to "#ae17e1",
            "byr" to "1931",
            "hgt" to "179cm"
        )
        val passport4: Map<String, String> = emptyMap()
        val verifier: PassportVerifier = PassportVerifierImpl()

        verifier.mandatoryFields = setOf("ecl", "iyr", "eyr", "pid", "hcl", "byr", "hgt")

        assert(verifier.checkPassport(passport))
        assertFalse(verifier.checkPassport(passport2))
        assert(verifier.checkPassport(passport3))
        assertFalse(verifier.checkPassport(passport4))
    }

    @Test
    fun test_passportVerifier_checkRegex() {
        val passport: Map<String, String> = mapOf(
            "iyr" to "2013",
            "ecl" to "brn",
            "cid" to "324",
            "eyr" to "2024",
            "pid" to "760753108",
            "hcl" to "#ae17e1",
            "byr" to "1931",
            "hgt" to "179cm"
        )
        val passport2: Map<String, String> = mapOf(
            "iyr" to "2013",
            "ecl" to "brn",
            "cid" to "324",
            "eyr" to "2024",
            "pid" to "760753108",
            "hcl" to "#ae17e1",
            "byr" to "1931",
            "hgt" to "179in"
        )
        val passport3: Map<String, String> = mapOf(
            "ecl" to "brn",
            "cid" to "324",
            "eyr" to "2024",
            "pid" to "760753108"
        )
        val rules: Map<String, Regex> = mapOf(
            "iyr" to """^20[0-2]3$""".toRegex(),
            "hgt" to """^.+cm$""".toRegex()
        )

        val passport4: Map<String, String> = emptyMap()
        val verifier: PassportVerifier = PassportVerifierImpl()

        verifier.fieldRegexRules = rules

        assert(verifier.checkPassport(passport))
        assertFalse(verifier.checkPassport(passport2))
        assert(verifier.checkPassport(passport3))
        assert(verifier.checkPassport(passport4))
    }
}