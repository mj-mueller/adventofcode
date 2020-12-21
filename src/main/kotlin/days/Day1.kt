package days

class Day1(filename: String) : AbstractDay(filename) {
    override fun part1(): Int {
        val lines = inputAsIntLines()
        for (number1 in lines)
            for (number2 in lines)
                if (number1 + number2 == 2020)
                    return number1 * number2
        throw IllegalArgumentException("No combination of two numbers sums up to 2020.")
    }

    override fun part2(): Int {
        val lines = inputAsIntLines()
        for (number1 in lines)
            for (number2 in lines)
                for (number3 in lines)
                    if (number1 + number2 + number3 == 2020)
                        return number1 * number2 * number3
        throw IllegalArgumentException("No combination of three numbers sums up to 2020.")
    }
}