package com.muellermoritz.spring.service

import org.springframework.stereotype.Service

@Service
interface DayService {

    fun solve(day: Int, part: Int, text: String): Int

    fun availableDays(): List<String>
}