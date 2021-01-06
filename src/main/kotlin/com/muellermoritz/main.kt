import com.muellermoritz.days.*
import com.muellermoritz.utils.ResourcesRequester
import com.muellermoritz.utils.ResourcesRequester.Companion.getInputFileAsText

fun main() {
    val day1 = Day1(getInputFileAsText("day1.txt"))
    println(day1.getResults())
    val day2 = Day2(ResourcesRequester.DAY_INPUT_PATH + "day2.txt")
    day2.printResults()
    val day3 = Day3(ResourcesRequester.DAY_INPUT_PATH + "day3.txt")
    day3.printResults()
    val day4 = Day4(ResourcesRequester.DAY_INPUT_PATH + "day4.txt")
    day4.printResults()
    val day5 = Day5(ResourcesRequester.DAY_INPUT_PATH + "day5.txt")
    day5.printResults()
    val day6 = Day6(ResourcesRequester.DAY_INPUT_PATH + "day6.txt")
    day6.printResults()
}