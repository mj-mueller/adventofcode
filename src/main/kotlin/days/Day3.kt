package days

import kotlin.math.max

class Day3(private val input: List<String>) {

    private val decompose: Regex = """^([0-9]+)-([0-9]+) ([a-z]): (.*)$""".toRegex()

    fun part1() = slide(down1, method1)

    private fun slide(rows: List<Int>, stepFunction: (Int) -> Int) =
        rows.map {  calulatePos(it,stepFunction)}.mapIndexed { rowindex, position -> input[rowindex][position] }.filter { it == '#' }
           .count()

    open fun calulatePos(steps: Int, stepFunction: (Int) -> Int): Int{
        if(steps < 1){
            return 0
        }
        else
//            println("stepss $steps")
            return   stepFunction(calulatePos(steps-1, stepFunction)) % input[0].length
    }

    val down1 = input.indices.toList()
    val down2 = input.indices.filter {  it % 2 == 0 }

    val method1: (Int) -> Int = { it + 1}
    val method2: (Int) -> Int = { it + 3 }
    val method3: (Int) -> Int = { it + 5 }
    val method4: (Int) -> Int = { it + 7 }


    fun part2() {
        println(down1)
        println(down2.toString())
        println(            slide(down1, method1).toString())
        listOf(method1, method2, method3, method4).mapNotNull { slide(down1, it) }.plus(slide(down2, method1)).toList().toString()


    }
}