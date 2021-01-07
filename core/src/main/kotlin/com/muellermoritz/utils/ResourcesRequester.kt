package com.muellermoritz.utils

import mu.KotlinLogging
import java.net.URL

private val logger = KotlinLogging.logger {}

class ResourcesRequester {

    //TODO: Maybe common module with resources and this sort of methods?
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

//      private  fun getResourcePath(filename: String): URL =
//          getResource(DAY_INPUT_PATH + filename)
//    }


        fun getInputFileAsText(filename: String) = getResource(DAY_INPUT_PATH + filename).readText()

    }
}