plugins {
    application
    // Spring
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")

    // Swagger
    id("io.swagger.core.v3.swagger-gradle-plugin")

//    id("com.github.johnrengelman.processes") version "0.5.0"
//    id("org.springdoc.openapi-gradle-plugin") version "1.3.0"
}

description = "Webservice for advent of code 2020 project."
version = "0.1-SNAPSHOT"

dependencies {
    implementation(project(":aoc-core"))

    val swaggerVersion: String by ext
    val springBootVersion: String by ext

    // Logging configuration
    configurations.all {
        // Exclude this because springs logback would conflict with log4j+slf4j
        exclude("org.springframework.boot", "spring-boot-starter-logging")
    }

    // Swagger
    implementation("io.springfox:springfox-swagger-ui:$swaggerVersion")
    implementation("io.springfox:springfox-swagger2:$swaggerVersion")

    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-freemarker:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-devtools:$springBootVersion")
    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
    {
        exclude("org.junit.vintage:junit-vintage-engine")
    }
}

// Configurations ***************************************************************

springBoot {
    mainClass.set("com.muellermoritz.spring.AoCRESTApplicationKt")
}

//resolve {
//    outputFileName = "PetStoreAPI"
//    outputFormat = "JSON"
//    prettyPrint = "TRUE"
//    classpath = sourceSets.main.runtimeClasspath
//    resourcePackages = ["io.test"]
//    outputDir = file("test")
//}