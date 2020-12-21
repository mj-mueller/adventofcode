import days.Day1
import days.Day2
import days.Day3
import days.Day4
import days.utils.ResourcesRequester

fun main() {
    val day1 = Day1(ResourcesRequester.DAY_INPUT_PATH + "day1.txt")
    day1.printResults()
    val day2 = Day2(ResourcesRequester.DAY_INPUT_PATH + "day2.txt")
    day2.printResults()
    val day3 = Day3(ResourcesRequester.DAY_INPUT_PATH + "day3.txt")
    day3.printResults()
    val day4 = Day4(ResourcesRequester.DAY_INPUT_PATH + "day4.txt")
    day4.printResults()
}