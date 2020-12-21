package days.day4

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PassportReaderTest {

    @Test
    fun test_passportReader_readEmptyPassportFile_shouldBeZeroPassports() {
        val input = emptyList<String>()
        val read = PassportReader(input)

        assertEquals(0, read.passports.size)
    }

    @Test
    fun test_passportReader_readPassportFile() {
        val passport1 = mapOf(
            "iyr" to "2013",
            "ecl" to "amb",
            "cid" to "350",
            "eyr" to "2023",
            "pid" to "028048884",
            "hcl" to "#cfa07d",
            "byr" to "1929"
        )
        val passport2 = mapOf(
            "iyr" to "2013",
            "ecl" to "brn",
            "cid" to "324",
            "eyr" to "2024",
            "pid" to "760753108",
            "hcl" to "#ae17e1",
            "byr" to "1931",
            "hgt" to "179cm"
        )
        val passport3 = mapOf(
            "iyr" to "2013",
            "eyr" to "2024",
            "hcl" to "#ae17e1"
        )
        val input = listOf("""            iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
            hcl:#cfa07d byr:1929""","""

            hcl:#ae17e1 iyr:2013
            eyr:2024
            ecl:brn pid:760753108 byr:1931
            hgt:179cm cid:324
            ""","""
            hcl:#ae17e1 iyr:2013
            eyr:2024
        """)


        val reader = PassportReader(input)

        assertEquals(3, reader.passports.size)

        val readPassport1 = reader.passports[0]
        val readPassport2 = reader.passports[1]
        val readPassport3 = reader.passports[2]


        assertEquals(passport1, readPassport1)
        assertEquals(passport2, readPassport2)
        assertEquals(passport3, readPassport3)
    }
}