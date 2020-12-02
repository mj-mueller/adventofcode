package days

class Day1(val input: List<String> ) {

    fun part1(): Int {
        val numbers = input.map { it.toInt() }
        for (number1 in numbers)
            for ( number2 in numbers)
                if (number1 + number2 == 2020)
                    return number1 * number2
        return -1
    }

    fun part2(): Int {
        val numbers = input.map { it.toInt() }
        for (number1 in numbers)
            for ( number2 in numbers)
                for (number3 in numbers)
                if (number1 + number2 + number3 == 2020)
                    return number1 * number2 * number3
        return -1
    }
}