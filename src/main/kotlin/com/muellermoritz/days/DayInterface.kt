package com.muellermoritz.days

interface DayInterface {
    fun getResults() = "part1: ${part1()}\n part2: ${part2()}"

    fun part1(): Int

    fun part2(): Int
}