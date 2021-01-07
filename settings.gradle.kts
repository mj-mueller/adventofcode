rootProject.name = "Advent_of_Code_2020"

// Submodules
include("core", "cli", "webservice")

rootProject.children.forEach {project ->
    project.buildFileName = "${project.name}.gradle.kts"
}
project(":core").name = "aoc-core"
project(":cli").name = "aoc-cli"
project(":webservice").name = "aoc-ws"
