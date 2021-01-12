plugins {
    application
    jacoco
}

description = "Command line application for the advent of code 2020 project."
version = "1.0-SNAPSHOT"

dependencies {
    implementation(project(":aoc-core"))
}

// Configurations ***************************************************************

application {
    mainClass.set("MainKt")
}