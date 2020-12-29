package com.muellermoritz.utils

import java.net.URL
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
class ResourcesRequester {
    companion object {
        const val DAY_INPUT_PATH = "/input/"
        fun getResource(filename: String): URL {
            val resource: URL? = ResourcesRequester::class.java.getResource(filename)
            if (resource == null) {
                val e = IllegalArgumentException("Could not find resource $filename!")
                logger.error("Error requesting resource", e)
                throw e
            }
            return resource
        }
    }
}