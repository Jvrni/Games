pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Games"
include(":app")
include(":core")
include(":features")
include(":core:designSystem")
include(":core:common")
include(":core:destionations")
include(":core:domain")
include(":core:service")
include(":features:splash")
include(":features:home")
include(":features:details")
