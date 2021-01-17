plugins{
    id("com.dorongold.task-tree")
    id("io.snyk.gradle.plugin.snykplugin")
    id("org.sonarqube")
}

//// Configurations ***************************************************************


val sonarToken: String by project
val sonarDockerContainerImageName = "sonarqube:latest"
val sonarDockerContainerPortMapping = "9000:9000"
val sonarDockerContainerName = "sonarqube"

sonarqube {
    properties {
        property("sonar.login", sonarToken)
    }
}


// Load API token from user folder. Fallback to env for travis. There should be a combined way for this.
val snykAPITokenFromProperty: String? by project
val snykAPITokenFromEnv: String? = System.getenv("SNYK_API_TOKEN")



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
//
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