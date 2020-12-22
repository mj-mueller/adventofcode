import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.21"
    application
    idea
    jacoco
    id("org.jetbrains.dokka") version "1.4.20"
    id("io.snyk.gradle.plugin.snykplugin") version "0.4"
    id("com.dorongold.task-tree") version "1.5"
    // For code coverage transmit from Travis
    id("com.github.nbaztec.coveralls-jacoco") version "1.2.4"
    id("org.sonarqube") version "3.0"
}


version = "0.5-SNAPSHOT"
description =
    "The aim is to solve the GitHub Advent of Code 2020 code challenges while trying out concepts and" +
            " features such as TDD, CI (travis), tags, clean code methods amd more."
group = "Mo"

// Versions
val log4jVersion = "2.14.0"
val jUnitVersion = "5.7.0"
val gradleProjectVersion = "6.7.1"
val sonarDockerContainerImage = "sonarqube:latest"

// Load API token from user folder. Fallback to env for travis. There should be a combined way for this.
val snykAPITokenFromProperty: String? by project
val snykAPITokenFromEnv: String? = System.getenv("SNYK_API_TOKEN")
val sonarToken: String by project


repositories {
    mavenCentral()
    jcenter()
}

buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
}

dependencies {
    implementation("org.apache.logging.log4j:log4j-api:$log4jVersion")
    implementation("org.apache.logging.log4j:log4j-core:$log4jVersion")
    implementation("org.junit.jupiter:junit-jupiter:$jUnitVersion")
}

// Configurations ***************************************************************

sonarqube {
    properties {
        property("sonar.login", sonarToken)
    }
}

idea {
    module {
        // Download sources and javadoc
        isDownloadJavadoc = true
        isDownloadSources = true
        excludeDirs = setOf(".gradle", ".idea", "build").map { file(it) }.toSet()
    }
}

application {
    mainClass.set("MainKt")
}

snyk {
    setAutoDownload(true)
    setAutoUpdate(false)
    setApi(snykAPITokenFromProperty ?: snykAPITokenFromEnv)
    setSeverity("low")
}

// Task Configurations ***************************************************************

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
    reports {
        html.isEnabled = true
        xml.isEnabled = true
        csv.isEnabled = false
        html.destination = file("${buildDir}/jacocoHtml")
    }
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

tasks.wrapper {
    gradleVersion = gradleProjectVersion
    distributionType = Wrapper.DistributionType.ALL
}

// Custom tasks ***************************************************************

tasks.register<Exec>("deleteSonarDocker") {
    dependsOn(":stopSonarDocker")
    group = "sonar"
    description = "Delete SonarQube Docker container."
    isIgnoreExitValue = true
    commandLine("docker")
    args("rm", "sonarqube")
}

tasks.register<Exec>("createSonarDocker") {
    dependsOn(":deleteSonarDocker")
    group = "sonar"
    description = "Creating SonarQube Docker container."
    commandLine("docker")
    args("run", "-d", "--name=sonarqube", "-p=9000:9000", sonarDockerContainerImage)
}

tasks.register<Exec>("startSonarDocker") {
    group = "sonar"
    description = "Starting SonarQube Docker container."
    commandLine("docker")
    args("start", "sonarqube")
}

tasks.register<Exec>("listContainers") {
    group = "sonar"
    description = "List all docker containers."
    commandLine("docker")
    args("container", "list", "--all")
}

tasks.register<Exec>("stopSonarDocker") {
    description = "Stopping SonarQube Docker container."
    group = "sonar"
    isIgnoreExitValue = true
    commandLine("docker")
    args("stop", "sonarqube")
}