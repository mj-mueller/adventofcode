package com.muellermoritz.utils.logging

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

interface Logging {
    val log: Logger
        get() = LogManager.getLogger(this.javaClass.name)
}