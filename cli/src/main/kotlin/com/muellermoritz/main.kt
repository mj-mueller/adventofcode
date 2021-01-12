import com.muellermoritz.days.*
import com.muellermoritz.utils.ResourcesRequester.Companion.getInputFileAsText

fun main() {
    val day1 = Day1(getInputFileAsText("day1.txt"))
    println(day1.getResults())
    val day2 = Day2(getInputFileAsText("day2.txt"))
    println(day2.getResults())
    val day3 = Day3(getInputFileAsText("day3.txt"))
    println(day3.getResults())
    val day4 = Day4(getInputFileAsText("day4.txt"))
    println(day4.getResults())
    val day5 = Day5(getInputFileAsText("day5.txt"))
    println(day5.getResults())
    val day6 = Day6(getInputFileAsText("day6.txt"))
    println(day6.getResults())
}