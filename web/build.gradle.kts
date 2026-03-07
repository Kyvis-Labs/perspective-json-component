import com.github.gradle.node.yarn.task.YarnTask
import com.github.gradle.node.npm.task.NpmTask

plugins {
    `java-library`
    id("com.github.node-gradle.node") version("3.2.1")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

val projectOutput: String by extra("$buildDir/generated-resources/")

node {
    version.set("20.11.0")
    yarnVersion.set("1.22.22")
    npmVersion.set("10.2.4")
    download.set(true)
    nodeProjectDir.set(file(project.projectDir))
}

val yarnPackages by tasks.registering(YarnTask::class) {
    description = "Installs npm dependencies via yarn."
    args.set(listOf("install", "--verbose"))

    inputs.files(
        fileTree(project.projectDir).matching {
            include("**/package.json", "**/yarn.lock")
        }
    )

    outputs.dirs(
        file("node_modules"),
        file("packages/client/node_modules")
    )

    dependsOn("${project.path}:yarn", ":web:npmSetup")
}

val webpack by tasks.registering(NpmTask::class) {
    group = "Ignition Module"
    description = "Runs 'npm run build' to execute webpack."

    args.set(listOf("run", "build"))

    dependsOn(yarnPackages)

    inputs.files(project.fileTree("packages").matching {
        exclude("**/node_modules/**", "**/dist/**", "**/.awcache/**", "**/yarn-error.log")
    }.toList())

    outputs.files(fileTree(projectOutput))
}

val deleteDistFolders by tasks.registering(Delete::class) {
    delete(file("packages/client/dist/"))
}

tasks {
    processResources {
        dependsOn(webpack, yarnPackages)
    }

    clean {
        dependsOn(deleteDistFolders)
    }
}

val deepClean by tasks.registering {
    doLast {
        delete(file("packages/client/node_modules"))
        delete(file("packages/client/.gradle"))
        delete(file(".gradle"))
        delete(file("node_modules"))
    }

    dependsOn(project.tasks.named("clean"))
}

project(":gateway")?.tasks?.named("processResources")?.configure {
    dependsOn(webpack)
}

sourceSets {
    main {
        output.dir(projectOutput, "builtBy" to listOf(webpack))
    }
}
