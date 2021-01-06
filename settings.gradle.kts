pluginManagement {
    val kotlinVersion: String by settings
    val gradlePluginPluginSpring: String by settings
    val gradlePluginSpringBoot: String by settings
    val gradlePluginSpringDependencyManagement: String by settings
    val gradlePluginDokka: String by settings
    val gradlePluginSnyk: String by settings
    val gradlePluginTaskTree: String by settings
    val gradlePluginCoveralls: String by settings
    val gradlePluginSonarQube: String by settings
    val gradlePluginSwagger: String by settings

    plugins {
        id("org.jetbrains.kotlin.jvm").version(kotlinVersion)
        id("org.jetbrains.kotlin.plugin.spring").version(gradlePluginPluginSpring)
        id("org.springframework.boot").version(gradlePluginSpringBoot)
        id("io.spring.dependency-management").version(gradlePluginSpringDependencyManagement)
        id("org.jetbrains.dokka").version(gradlePluginDokka)
        id("io.snyk.gradle.plugin.snykplugin").version(gradlePluginSnyk)
        id("com.dorongold.task-tree").version(gradlePluginTaskTree)
        id("com.github.nbaztec.coveralls-jacoco").version(gradlePluginCoveralls)
        id("org.sonarqube").version(gradlePluginSonarQube)
        id("io.swagger.core.v3.swagger-gradle-plugin").version(gradlePluginSwagger)
    }
}

val pName: String by settings
rootProject.name = pName

//include("lib")
include("spring")
//include("App")