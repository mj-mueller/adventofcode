plugins {
    application
    jacoco
}

description = "Command line application for the advent of code 2020 project."
version = "1.0-SNAPSHOT"

dependencies {
    implementation(project(":aoc-core"))

    val micrometerRegistry: String by ext

    implementation("io.micrometer:micrometer-registry-graphite:$micrometerRegistry")
    implementation("io.micrometer:micrometer-registry-prometheus:$micrometerRegistry")
    implementation("io.prometheus:simpleclient_httpserver:0.9.0")
    implementation("io.prometheus:simpleclient_hotspot:0.9.0")
    implementation("io.prometheus:simpleclient:0.9.0")
}

// Configurations ***************************************************************

application {
    mainClass.set("MainKt")
}