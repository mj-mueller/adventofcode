plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    // Plugins
    implementation("org.jetbrains.kotlin", "kotlin-gradle-plugin", "1.4.21")
    implementation("gradle.plugin.com.dorongold.plugins", "task-tree", "1.5")
    implementation(
        "org.jetbrains.kotlin.plugin.spring",
        "org.jetbrains.kotlin.plugin.spring.gradle.plugin",
        "1.4.30-M1"
    )
    implementation("org.springframework.boot", "spring-boot-gradle-plugin", "2.4.2")
    implementation("io.spring.dependency-management", "io.spring.dependency-management.gradle.plugin", "1.0.10.RELEASE")
    implementation("gradle.plugin.io.snyk.gradle.plugin", "snyk", "0.4")
    implementation("org.sonarqube", "org.sonarqube.gradle.plugin", "3.0")
    implementation("org.jetbrains.dokka", "dokka-gradle-plugin", "1.4.20")
    implementation("io.swagger.core.v3", "swagger-gradle-plugin", "2.1.6")
    implementation("gradle.plugin.com.github.jengelman.gradle.plugins", "gradle-processes", "0.5.0")
    implementation("org.springdoc", "springdoc-openapi-gradle-plugin", "1.3.0")
}