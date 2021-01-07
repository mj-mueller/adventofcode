package com.muellermoritz.spring.service

import com.muellermoritz.days.Day1
import org.springframework.stereotype.Component

@Component
class DayServiceImpl : DayService {

    override fun solve(day: Int, part: Int, text: String): Int {
        TODO("Not yet implemented")
    }

    override fun availableDays(): List<String> = emptyList()

    companion object {
        // Load days statically
        val s = setOf(
            Day1::class
        )//.map {it.primaryConstructor!!.call("")}
    }
}