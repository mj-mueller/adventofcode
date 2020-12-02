package days

class Day2(private val input: List<String>) {

    private val decompose: Regex = """^([0-9]+)-([0-9]+) ([a-z]): (.*)$""".toRegex()

    private fun checkLines(lines:List<String>, checkFunction: (min: Int, max: Int, letter: Char, password: String) -> Boolean) = input.mapNotNull { decompose.find(it) }.map {
        checkFunction(
            it.groupValues[1].toInt(),
            it.groupValues[2].toInt(),
            it.groupValues[3].single(),
            it.groupValues[4]
        )
    }.filter { it }.count()

    fun part1() = input.mapNotNull { decompose.find(it) }.map {
        checkPolicyPart1(
            it.groupValues[1].toInt(),
            it.groupValues[2].toInt(),
            it.groupValues[3].single(),
            it.groupValues[4]
        )
    }.filter { it }.count()


    private fun checkPolicyPart1(min: Int, max: Int, letter: Char, password: String) =
        password.filter { it == letter }.count() in min..max

    private fun checkPolicyPart2(pos1: Int, pos2: Int, letter: Char, password: String) =
        (password[pos1+1] == letter) xor (password[pos2+1]==letter)

    fun part2() = input.mapNotNull { decompose.find(it) }.map {
        checkPolicyPart2(
            it.groupValues[1].toInt(),
            it.groupValues[2].toInt(),
            it.groupValues[3].single(),
            it.groupValues[4]
        )
    }.filter { it }.count()
}