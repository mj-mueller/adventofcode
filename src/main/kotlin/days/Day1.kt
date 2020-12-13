package days

class Day1(filename: String) : Day(filename) {
    override fun part1(): Int {
        var result = 0
        val lines = inputAsIntLines()
        for (number1 in lines)
            for (number2 in lines)
                if (number1 + number2 == 2020)
                    result += number1 * number2
        return result
    }

    override fun part2(): Int {
        var result = 0
        val lines = inputAsIntLines()
        for (number1 in lines)
            for (number2 in lines)
                for (number3 in lines)
                    if (number1 + number2 + number3 == 2020)
                        result += number1 * number2 * number3
        return result
    }
}