package com.muellermoritz.spring.controller

import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Hello world REST controller.
 *
 * @author mj-mueller
 * @date 01/06/2020
 */
@Api(value = "Root level interface", tags = ["HelloWorld"])
@RequestMapping
class HelloWorldController {
    @ApiOperation(value = "Get detailed information of students through student number")
    @ApiImplicitParam(name = "studentId", value = "Student number", required = true, dataType = "String")
    @GetMapping("/")
    fun blog(model: Model): String {
        model["title"] = "Blog"
        return "Hi"
    }
}