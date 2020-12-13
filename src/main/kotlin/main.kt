import days.Day1
import days.Day2
import java.io.File

fun main() {
    val day1 = Day1("./src/main/resources/days/day1.txt")
    day1.printResults()
    val day2 = Day2("./src/main/resources/days/day2.txt")
    day2.printResults()
}