plugins {
    id("aoc.kotlin-library-conventions")
}

description = "Core module for advent of code 2020 project."
version = "0.1-SNAPSHOT"

dependencies {
    implementation(Libs.Micrometer.micrometer_core)
}