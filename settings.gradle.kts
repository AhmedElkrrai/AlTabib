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
includeBuild("build-logic")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "AlTabib"
include(":app")
include(":libraries:core")
include(":libraries:design-system")
include(":featuers:user:domain")
include(":featuers:user:data")
include(":featuers:settings:domain")
include(":featuers:settings:data")
include(":featuers:favorites:domain")
include(":featuers:doctors:domain")
include(":featuers:doctors:data")
include(":featuers:appointments:domain")
include(":featuers:appointments:data")
include(":featuers:user:presentation")
include(":libraries:signin")
include(":featuers:settings:presentation")
