import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.21"
    id("org.jetbrains.dokka") version "1.4.20"
    application
    idea
    id("io.snyk.gradle.plugin.snykplugin") version "0.4"
}

group = "Mo"
version = "0.4-SNAPSHOT"

// Versions
val log4jVersion = "2.14.0"
val jUnitVersion = "5.7.0"

// Load API token from user folder
val snykAPIToken: String by project

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("org.apache.logging.log4j:log4j-api-kotlin:1.0.0")
    implementation("org.apache.logging.log4j:log4j-api:$log4jVersion")
    implementation("org.apache.logging.log4j:log4j-core:$log4jVersion")
    implementation("org.junit.jupiter:junit-jupiter:$jUnitVersion")
    implementation("org.junit.jupiter:junit-jupiter:$jUnitVersion")
    testImplementation(kotlin("test-junit"))
//    testImplementation("io.mockk:mockk-dsl-jvm:1.10.3-jdk8")
}

tasks.wrapper {
    gradleVersion = "6.7.1"
    distributionType = Wrapper.DistributionType.ALL
}

idea {
    module {
        // Download sources and javadoc
        isDownloadJavadoc = true
        isDownloadSources = true
        excludeDirs = setOf(".gradle", ".idea", "build").map { file(it) }.toSet()
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}

snyk {
    setAutoDownload(true)
    setAutoUpdate(false)
    setApi(snykAPIToken)
    setSeverity("low")
}