package com.muellermoritz

import main
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

internal class MainTest {
    @Test
    fun runMainWithoutException() {
        assertDoesNotThrow {
            main()
        }
    }
}