package days.utils

import org.apache.logging.log4j.LogManager
import java.net.URL

class ResourcesRequester {
    companion object {
        const val DAY_INPUT_PATH = "/input/"
        private val logger = LogManager.getLogger(ResourcesRequester::class.java.name)
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