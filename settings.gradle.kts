enableFeaturePreview("VERSION_CATALOGS")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
//        maven { url = uri("https://maven.google.com") }
//        maven { url = uri("https://jitpack.io") }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
//        maven { url = uri("https://maven.google.com") }
//        maven { url = uri("https://jitpack.io") }
    }

    versionCatalogs {
        create("libs") {
            from(files("gradle/libraries.versions.toml"))
        }
    }
}

rootProject.name = "Tmdb-KMM"
rootProject.buildFileName = "build.gradle.kts"

include(":androidApp")
include(":shared")
include(":data:api:model")
include(":data:api:config")
include(":data:api:impl")
include(":data:source:remote:contract")
include(":data:source:remote:impl")
include(":data:model")
include(":store:action")
include(":store:app")
include(":store:base")
include(":store:env")
include(":store:feature")
include(":store:reducer")
include(":store:state")
include(":util")
