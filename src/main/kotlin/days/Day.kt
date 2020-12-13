package days

import java.io.File

abstract class Day(file: String) {
    private val input: String = File(file).readText()

    fun inputAsLines(): List<String> = input.lines()

    fun inputAsIntLines(): List<Int> = input.lines().map { it.toInt() }

    fun printResults() {
        println("${this.javaClass.simpleName} part1: ${part1()}")
        println("${this.javaClass.simpleName} part2: ${part2()}")
    }

    abstract fun part1(): Int

    abstract fun part2(): Int
}