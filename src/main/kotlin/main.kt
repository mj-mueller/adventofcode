import days.Day1
import days.Day2
import days.Day3
import java.io.File

fun main() {
    val day1 = Day1(File("./src/main/resources/days/day1.txt").readLines())
    println("Day1 part1: ${day1.part1()}")
    println("Day1 part2: ${day1.part2()}")
    val day2 = Day2(File("./src/main/resources/days/day2.txt").readLines())
    println("Day2 part1: ${day2.part1()}")
    println("Day2 part2: ${day2.part2()}")
    val day3 = Day3(File("./src/main/resources/days/day3_sample.txt").readLines())
    println("Day3 part1: ${day3.part1()}")
    println("Day3 part2: ${day3.part2()}")
}