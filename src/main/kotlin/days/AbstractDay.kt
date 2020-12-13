package days

import org.apache.logging.log4j.kotlin.Logging
import java.io.File

abstract class AbstractDay(filename: String): Logging {
    private val input: String = File(filename).readText()

    fun inputAsLines(): List<String> = input.lines()

    fun inputAsIntLines(): List<Int> = input.lines().map { it.toInt() }

    fun printResults() {
        logger.info("${this.javaClass.simpleName} part1: ${part1()}")
        logger.info("${this.javaClass.simpleName} part2: ${part2()}")
    }

    abstract fun part1(): Int

    abstract fun part2(): Int
}
