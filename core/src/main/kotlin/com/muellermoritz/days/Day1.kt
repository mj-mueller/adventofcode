package com.muellermoritz.days

import com.muellermoritz.days.utils.AoCCoreMetricRegistry
import com.muellermoritz.days.utils.InputUtils.Companion.inputAsIntLines
import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.Metrics
import io.micrometer.core.instrument.Timer
import io.micrometer.core.instrument.simple.SimpleMeterRegistry

import java.util.concurrent.Callable


class Day1(private val text: String) : DayInterface {
        override fun part1(): Int {
        var res = -1
        Metrics.globalRegistry.timer(TIMER_PART1).record {
            res = part1Impl()
        }
        return res
    }

    private fun part1Impl(): Int {
        val lines = inputAsIntLines(text)
        for (number1 in lines)
            for (number2 in lines)
                if (number1 + number2 == 2020)
                    return number1 * number2
        throw IllegalArgumentException("No combination of two numbers sums up to 2020.")
    }

    override fun part2(): Int {
        val lines = inputAsIntLines(text)
        for (number1 in lines)
            for (number2 in lines)
                for (number3 in lines)
                    if (number1 + number2 + number3 == 2020)
                        return number1 * number2 * number3
        throw IllegalArgumentException("No combination of three numbers sums up to 2020.")
    }

    companion object {
        val TIMER_PART1 = "timer for part1"
    }
}