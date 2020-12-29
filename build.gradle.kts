plugins {
    // Kotlin
    id("org.jetbrains.kotlin.jvm")
    application

    // Spring
//    id("org.jetbrains.kotlin.plugin.spring")
//    id("org.springframework.boot")
//    id("io.spring.dependency-management")

    // Misc
    idea
    jacoco
    id("org.jetbrains.dokka")
    id("io.snyk.gradle.plugin.snykplugin")
    id("com.dorongold.task-tree")
    // For code coverage transmit from Travis
    id("com.github.nbaztec.coveralls-jacoco")
    id("org.sonarqube")
}


val pDescription: String by project
val pGroup: String by project
val pVersion: String by project
val javaLanguageLevel: String by project
val gradleWrapperVersion: String by project
val gradleDistributionVersion: String by project

val springBootFreemarker: String by project
val log4JVersion: String by project
val junitJupiterVersion: String by project
val kotlinLoggingVersion: String by project
val jacksonModuleKotlinVersion: String by project


// Load API token from user folder. Fallback to env for travis. There should be a combined way for this.
val snykAPITokenFromProperty: String? by project
val snykAPITokenFromEnv: String? = System.getenv("SNYK_API_TOKEN")
val sonarToken: String by project
val sonarDockerContainerImageName: String by project
val sonarDockerContainerPortMapping: String by project
val sonarDockerContainerName: String by project
val JUnitMaxParallelForks: String by project



description = pDescription
group = pGroup
version = pVersion


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
    implementation("org.apache.logging.log4j:log4j-api:$log4JVersion")
    implementation("org.apache.logging.log4j:log4j-core:$log4JVersion")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:$log4JVersion")

    implementation("io.github.microutils:kotlin-logging:$kotlinLoggingVersion")

    implementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonModuleKotlinVersion")

// Spring Boot
//    implementation("org.springframework.boot:spring-boot-starter")
//    implementation("org.springframework.boot:spring-boot-starter-web")
//    implementation("org.springframework.boot:spring-boot-starter-freemarker:$springBootFreemarker")
//    developmentOnly ("org.springframework.boot:spring-boot-devtools")
//    testImplementation("org.springframework.boot:spring-boot-starter-test")
//    {
//        exclude ("org.junit.vintage:junit-vintage-engine")
//    }
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

tasks.withType<Test>().all {
    testLogging {
        events("passed", "skipped", "failed")
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
    maxParallelForks = JUnitMaxParallelForks.toInt()
    ignoreFailures = true
}

snyk {
    setAutoDownload(true)
    setAutoUpdate(false)
    setApi(snykAPITokenFromProperty ?: snykAPITokenFromEnv)
    setSeverity("low")
}

// Task Configurations ***************************************************************

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
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
    description = "Generates gradlew[.bat] scripts"
    gradleVersion = gradleWrapperVersion
    distributionType = if (gradleDistributionVersion.equals("all")) {
        org.gradle.api.tasks.wrapper.Wrapper.DistributionType.ALL
    } else {
        org.gradle.api.tasks.wrapper.Wrapper.DistributionType.BIN
    }
}

// Custom tasks ***************************************************************

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