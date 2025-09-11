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

rootProject.name = "Projet1DIIAGE"
include(":app")

include(":core:ui")
include(":core:domain")
include(":core:data")

include(":features:characters:ui")
include(":features:characters:domain")
include(":features:characters:data")

include(":features:manageSpace:ui")
include(":features:manageSpace:domain")
include(":features:manageSpace:data")

include(":features:availibility:ui")
include(":features:availibility:domain")
include(":features:availibility:data")

include(":features:reservation:ui")
include(":features:reservation:domain")
include(":features:reservation:data")