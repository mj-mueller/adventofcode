object Versions {
    const val kotlin_logging = "1.12.0"
    const val log4JVersion = "2.14.0"
    const val junitJupiter = "5.7.0"
    const val micrometer = "1.6.3"
    const val springBoot = "2.4.1"
    const val swagger = "3.0.0"
}

object Libs {

    object Log4J : Version(Versions.log4JVersion) {
        val log4j_api = "org.apache.logging.log4j:log4j-api:$version"
        val log4j_core = "org.apache.logging.log4j:log4j-core:$version"
        val log4j_slf4j_impl = "org.apache.logging.log4j:log4j-slf4j-impl:$version"
    }

    const val kotlin_logging = "io.github.microutils:kotlin-logging:${Versions.kotlin_logging}"
    const val junit_jupiter = "org.junit.jupiter:junit-jupiter:${Versions.junitJupiter}"

    object SpringBoot : Version(Versions.springBoot) {
        val spring_boot_starter_web = "org.springframework.boot:spring-boot-starter-web:$version"
        val spring_boot_starter_freemarker = "org.springframework.boot:spring-boot-starter-freemarker:$version"
        val spring_boot_devtools = "org.springframework.boot:spring-boot-devtools:$version"
        val spring_boot_starter_test = "org.springframework.boot:spring-boot-starter-test:$version"
    }

    object Swagger : Version(Versions.swagger) {
        val springfox_swagger_ui = "io.springfox:springfox-swagger-ui:$version"
        val springfox_swagger2 = "io.springfox:springfox-swagger2:$version"
    }

    object Micrometer : Version(Versions.micrometer) {
        val micrometer_core = "io.micrometer:micrometer-core:$version"
        val micrometer_registry_graphite = "io.micrometer:micrometer-registry-graphite:$version"
        val micrometer_registry_prometheus = "io.micrometer:micrometer-registry-prometheus:$version"
    }
}

open class Version(@JvmField val version: String)