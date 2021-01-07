package com.muellermoritz.spring.controller

import com.muellermoritz.spring.service.DayService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/days")
class DayController {

    @Autowired
    lateinit var service: DayService


    @GetMapping("/days")
    fun daySummary(model: Model): String {
//        model["title"] = "Blog"
        return service.availableDays().toString()
    }
}