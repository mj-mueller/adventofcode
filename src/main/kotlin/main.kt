import days.Day1
import days.Day2
import days.Day3
import days.Day4
import org.apache.logging.log4j.kotlin.Logging
import java.io.File

fun main(){
    val resources = "./src/main/resources/input/"
    val day1 = Day1(resources + "day1.txt")
    day1.printResults()
    val day2 = Day2(resources + "day2.txt")
    day2.printResults()
    val day3 = Day3(resources + "day3.txt")
    day3.printResults()
    val day4 = Day4(resources + "day4.txt")
    day4.printResults()
}