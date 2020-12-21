import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.21"
    application
    idea
    jacoco
    id("org.jetbrains.dokka") version "1.4.20"
    id("io.snyk.gradle.plugin.snykplugin") version "0.4"
    id("com.dorongold.task-tree") version "1.5"
}

group = "Mo"
version = "0.4-SNAPSHOT"

// Versions
val log4jVersion = "2.14.0"
val jUnitVersion = "5.7.0"

// Load API token from user folder. Fallback to env for travis. There should be a combined way for this.
val snykAPITokenFromProperty: String? by project
val snykAPITokenFromEnv: String? = System.getenv("SNYK_API_TOKEN")


repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("org.apache.logging.log4j:log4j-api-kotlin:1.0.0")
    implementation("org.apache.logging.log4j:log4j-api:$log4jVersion")
    implementation("org.apache.logging.log4j:log4j-core:$log4jVersion")
    implementation("org.junit.jupiter:junit-jupiter:$jUnitVersion")
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
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
    reports {
        html.isEnabled = true
        xml.isEnabled = false
        csv.isEnabled = false
        html.destination = file("${buildDir}/jacocoHtml")
    }
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
    setApi(snykAPITokenFromProperty ?: snykAPITokenFromEnv)
    setSeverity("low")
}