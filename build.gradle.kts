plugins {
    kotlin("jvm").version("1.4.21") apply false
    jacoco
    application
    idea

    id("com.dorongold.task-tree").version("1.5")

    // Spring
    id("org.jetbrains.kotlin.plugin.spring").version("1.4.30-M1") apply false
    id("org.springframework.boot").version("2.4.1") apply false
    id("io.spring.dependency-management").version("1.0.10.RELEASE") apply false

    // Misc
    id("io.snyk.gradle.plugin.snykplugin").version("0.4")
    id("com.github.nbaztec.coveralls-jacoco").version("1.2.4")
    id("org.sonarqube").version("3.0")
    id("org.jetbrains.dokka").version("1.4.20")
    id("io.swagger.core.v3.swagger-gradle-plugin").version("2.1.6") apply false
    id("com.github.johnrengelman.processes").version("0.5.0") apply false
    id("org.springdoc.openapi-gradle-plugin").version("1.3.0") apply false
}

description =
    "The aim is to solve the GitHub Advent of Code 2020 code challenges while trying out concepts and features such as TDD, CI (travis), tags, clean code methods amd more."
version = "0.5-SNAPSHOT"

repositories {
    google() // For coveralls
    mavenCentral()
    jcenter()
}

buildscript {
    repositories {
        google() // For coveralls
        mavenCentral()
        jcenter()
    }
}

allprojects {
    group = "com.muellermoritz"

    tasks.withType<JavaCompile> {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }
}

subprojects {
    apply {
        plugin("org.jetbrains.kotlin.jvm")
        plugin("jacoco")
        plugin("idea")
        plugin("org.jetbrains.dokka")
    }
    repositories {
        mavenCentral()
        jcenter()
    }

    dependencies {

        ext {
            set("log4JVersion", "2.14.0")
            set("kotlinLoggingVersion", "1.12.0")
            set("junitJupiterVersion", "5.7.0")
            set("micrometerRegistry", "latest.release")
            set("swaggerVersion", "3.0.0")
            set("springBootVersion", "2.4.1")
            set("micrometerCoreVersion", "1.6.2")
        }
        val log4JVersion: String by ext
        val kotlinLoggingVersion: String by ext
        val junitJupiterVersion: String by ext

        implementation("org.apache.logging.log4j:log4j-api:$log4JVersion")
        implementation("org.apache.logging.log4j:log4j-core:$log4JVersion")
        implementation("org.apache.logging.log4j:log4j-slf4j-impl:$log4JVersion")
        implementation("io.github.microutils:kotlin-logging:$kotlinLoggingVersion")

        implementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")
    }

    idea {
        module {
            // Download sources and javadoc
            isDownloadJavadoc = true
            isDownloadSources = true
            excludeDirs = setOf(".gradle", ".idea", "build").map { file(it) }.toSet()
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
            exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        }
        maxParallelForks = 4
        ignoreFailures = true
        finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
    }


    tasks.jacocoTestReport {
        reports {
            html.isEnabled = true
            xml.isEnabled = true
            csv.isEnabled = false
            html.destination = file("${buildDir}/jacocoHtml")
        }
    }
//    jacoco {
//        applyTo(tasks.run.get())
//    }
//
//    tasks.register<JacocoReport>("applicationCodeCoverageReport") {
//        executionData(tasks.run.get())
//        sourceSets(sourceSets.main.get())
//    }
}

// Configurations ***************************************************************

// Load API token from user folder. Fallback to env for travis. There should be a combined way for this.
val snykAPITokenFromProperty: String? by project
val snykAPITokenFromEnv: String? = System.getenv("SNYK_API_TOKEN")
val sonarToken: String by project
val sonarDockerContainerImageName = "sonarqube:latest"
val sonarDockerContainerPortMapping = "9000:9000"
val sonarDockerContainerName = "sonarqube"

sonarqube {
    properties {
        property("sonar.login", sonarToken)
    }
}

snyk {
    setAutoDownload(true)
    setAutoUpdate(false)
    setApi(snykAPITokenFromProperty ?: snykAPITokenFromEnv)
    setSeverity("low")
}

// Custom tasks ***************************************************************
//tasks.register<Delete>("deleteAllLogs")
//{
//
//    delete(fileTree("dir/foo")) {
//        include(""**/*.ext")
//    }
//}
//}

tasks.register<Exec>("deleteSonarDocker") {
    dependsOn(":stopSonarDocker")
    group = "sonar"
    description = "Delete SonarQube Docker container."
    isIgnoreExitValue = true
    commandLine("docker")
    args("rm", sonarDockerContainerName)
}

tasks.register<Exec>("createSonarDocker") {
    dependsOn(":deleteSonarDocker")
    group = "sonar"
    description = "Creating SonarQube Docker container."
    commandLine("docker")
    args(
        "run",
        "-d",
        "--name=$sonarDockerContainerName",
        "-p=$sonarDockerContainerPortMapping",
        sonarDockerContainerImageName
    )
}

tasks.register<Exec>("startSonarDocker") {
    group = "sonar"
    description = "Starting SonarQube Docker container."
    commandLine("docker")
    args("start", sonarDockerContainerName)
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
    args("stop", sonarDockerContainerName)
}