import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
    application
    idea
    id("io.snyk.gradle.plugin.snykplugin") version "0.4"
}

group = "Mo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.logging.log4j:log4j-api-kotlin:1.0.0")
    implementation("org.apache.logging.log4j:log4j-api:2.11.1")
    implementation("org.apache.logging.log4j:log4j-core:2.11.1")
    implementation("org.junit.jupiter:junit-jupiter:5.4.2")
    implementation("org.junit.jupiter:junit-jupiter:5.4.2")
    testImplementation(kotlin("test-junit"))
}

tasks.wrapper() {
    gradleVersion = "6.7.1"
    distributionType = Wrapper.DistributionType.ALL
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}

snyk {
    setAutoDownload(true)
    setAutoUpdate(false)
//    setApi()
    setSeverity("low")
}