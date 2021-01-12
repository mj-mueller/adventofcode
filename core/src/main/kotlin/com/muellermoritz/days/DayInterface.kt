package com.muellermoritz.days

import io.micrometer.core.instrument.Metrics

interface DayInterface {
    val dayLabel: String

    val METRIC_PART1_EXECUTION: String get() = "${dayLabel}.part1.execution"
    val METRIC_PART2_EXECUTION: String get() = "${dayLabel}.part2.execution"

    fun getResults() = "${dayLabel}.part1: ${part1()}\n${dayLabel}.part2: ${part2()}"

    fun part1(): Int {
        return Metrics.globalRegistry.timer(METRIC_PART1_EXECUTION).record<Int> { part1Impl() }
    }

    fun part1Impl(): Int

    fun part2(): Int {
        return Metrics.globalRegistry.timer(METRIC_PART2_EXECUTION).record<Int> { part2Impl() }
    }

    fun part2Impl(): Int
}