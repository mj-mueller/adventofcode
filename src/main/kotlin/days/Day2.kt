package days

class Day2(input: String) : Day(input) {
    private val decompose: Regex = """^([0-9]+)-([0-9]+) ([a-z]): (.*)$""".toRegex()

    private fun checkLines(
        checkFunction: (min: Int, max: Int, letter: Char, password: String) -> Boolean
    ): Int {
        return inputAsLines().mapNotNull { decompose.find(it) }.map {
            checkFunction(
                it.groupValues[1].toInt(),
                it.groupValues[2].toInt(),
                it.groupValues[3].single(),
                it.groupValues[4]
            )
        }.filter { it }.count()
    }

    override fun part1(): Int {
        return checkLines { min, max, letter, password ->
            password.filter { it == letter }.count() in min..max
        }
    }

    override fun part2(): Int {
        return checkLines { pos1, pos2, letter, password ->
            (password[pos1 - +1] == letter) xor (password[pos2 - 1] == letter)
        }
    }
}