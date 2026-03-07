plugins {
    base
    id("io.ia.sdk.modl") version("0.3.0")
}

allprojects {
    version = "1.0.0"
    group = "com.kyvislabs"
}

ignitionModule {
    fileName.set("JsonViewComponent")
    name.set("JsonViewComponent")
    id.set("com.kyvislabs.jsonview")
    moduleVersion.set("${project.version}")
    moduleDescription.set("A module that adds an interactive JSON viewer component to Perspective.")
    requiredIgnitionVersion.set("8.3.0")
    license.set("license.html")

    moduleDependencies.put("com.inductiveautomation.perspective", "DG")

    projectScopes.putAll(
        mapOf(
            ":gateway" to "G",
            ":web" to "G",
            ":designer" to "D",
            ":common" to "GD"
        )
    )

    hooks.putAll(
        mapOf(
            "com.kyvislabs.jsonview.gateway.JsonViewGatewayHook" to "G",
            "com.kyvislabs.jsonview.designer.JsonViewDesignerHook" to "D"
        )
    )
    freeModule.set(true)
    requiredFrameworkVersion.set("9")
    skipModlSigning.set(true)
}

val deepClean by tasks.registering {
    dependsOn(allprojects.map { "${it.path}:clean" })
    description = "Executes clean tasks and removes node plugin caches."
    doLast {
        delete(file(".gradle"))
    }
}
