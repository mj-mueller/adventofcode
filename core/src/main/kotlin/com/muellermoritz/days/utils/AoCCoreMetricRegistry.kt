package com.muellermoritz.days.utils

import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.composite.CompositeMeterRegistry
import io.micrometer.core.instrument.simple.SimpleMeterRegistry

object AoCCoreMetricRegistry{
    val registry: MeterRegistry = CompositeMeterRegistry()
}