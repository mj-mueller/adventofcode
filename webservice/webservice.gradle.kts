plugins {
    id("aoc.kotlin-webservice-conventions")
}

description = "Webservice for advent of code 2020 project."
version = "0.1-SNAPSHOT"

dependencies {
    implementation(project(":aoc-core"))

    // Logging configuration
    configurations.all {
        // Exclude this because springs logback would conflict with log4j+slf4j
        exclude("org.springframework.boot", "spring-boot-starter-logging")
    }

    // Swagger
    implementation(Libs.Swagger.springfox_swagger_ui)
    implementation(Libs.Swagger.springfox_swagger2)

    // Spring Boot
    implementation(Libs.SpringBoot.spring_boot_starter_web)
    implementation(Libs.SpringBoot.spring_boot_devtools)
    implementation(Libs.SpringBoot.spring_boot_starter_freemarker)
    implementation(Libs.SpringBoot.spring_boot_starter_test)
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