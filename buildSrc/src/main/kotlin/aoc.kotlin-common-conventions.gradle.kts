plugins {
    id("aoc.plugins-util")
    id("aoc.plugins-experimental")

    kotlin("jvm")
    `jacoco`
    `idea`

    // Misc
    id("org.jetbrains.dokka")
}

description =
    "The aim is to solve the GitHub Advent of Code 2020 code challenges while trying out concepts and features such as TDD, CI (travis), tags, clean code methods amd more."
version = "0.5-SNAPSHOT"

repositories {
    google() // For coveralls
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

//allprojects {
//group = "com.muellermoritz"
//
//tasks.withType<JavaCompile> {
//    sourceCompatibility = "1.8"
//    targetCompatibility = "1.8"
//}
//
//tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
//    kotlinOptions {
//        freeCompilerArgs = listOf("-Xjsr305=strict")
//        jvmTarget = "1.8"
//    }
//}
//}
//
//subprojects {
//    apply {
//        plugin("org.jetbrains.kotlin.jvm")
//        plugin("jacoco")
//        plugin("idea")
//        plugin("org.jetbrains.dokka")
//    }
//    repositories {
//        mavenCentral()
//        jcenter()
//    }
//
//    dependencies {
//
//        ext {
//            set("log4JVersion", "2.14.0")
//            set("kotlinLoggingVersion", "1.12.0")
//            set("junitJupiterVersion", "5.7.0")
//            set("micrometerRegistry", "latest.release")
//            set("swaggerVersion", "3.0.0")
//            set("springBootVersion", "2.4.1")
//            set("micrometerCoreVersion", "1.6.2")
//        }
//        val log4JVersion: String by ext
//        val kotlinLoggingVersion: String by ext
//        val junitJupiterVersion: String by ext
//
//        implementation("org.apache.logging.log4j:log4j-api:$log4JVersion")
//        implementation("org.apache.logging.log4j:log4j-core:$log4JVersion")
//        implementation("org.apache.logging.log4j:log4j-slf4j-impl:$log4JVersion")
//        implementation("io.github.microutils:kotlin-logging:$kotlinLoggingVersion")
//
//        implementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")
//    }
//
//    idea {
//        module {
//            // Download sources and javadoc
//            isDownloadJavadoc = true
//            isDownloadSources = true
//            excludeDirs = setOf(".gradle", ".idea", "build").map { file(it) }.toSet()
//        }
//    }
//
//    tasks.withType<Test> {
//        useJUnitPlatform()
//        testLogging {
//            events("passed", "skipped", "failed")
//            exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
//        }
//        maxParallelForks = 4
//        ignoreFailures = true
//        finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
//    }
//
//
//    tasks.jacocoTestReport {
//        reports {
//            html.isEnabled = true
//            xml.isEnabled = true
//            csv.isEnabled = false
//            html.destination = file("${buildDir}/jacocoHtml")
//        }
//    }
////    jacoco {
////        applyTo(tasks.run.get())
////    }
////
////    tasks.register<JacocoReport>("applicationCodeCoverageReport") {
////        executionData(tasks.run.get())
////        sourceSets(sourceSets.main.get())
////    }
//}
//
