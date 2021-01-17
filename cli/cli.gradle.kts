plugins {
    id("aoc.kotlin-application-conventions")
}

description = "Command line application for the advent of code 2020 project."
version = "1.0-SNAPSHOT"

dependencies {
    implementation(project(":aoc-core"))
}