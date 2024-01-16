pluginManagement {
    val kotlinVersion: String by settings

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.jetbrains.kotlin.jvm",
                "org.jetbrains.kotlin.plugin.spring",
                "org.jetbrains.kotlin.plugin.jpa" -> useVersion(kotlinVersion)
            }
        }
    }
}

rootProject.name = "fospay"
include("membership-service")
include("common")
include("banking-service")
include("money-service")
include("logging-consumer")
include("task-consumer")
include("remittance-service")
