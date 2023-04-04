enableFeaturePreview("VERSION_CATALOGS")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    // Replaced `RepositoriesMode.FAIL_ON_PROJECT_REPOS` to let web project run
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()

        // Workaround for js target to work with option `RepositoriesMode.PREFER_SETTINGS`
        // https://youtrack.jetbrains.com/issue/KT-51379/Build-fails-when-using-RepositoriesMode.FAILONPROJECTREPOS-with-kotlin-multiplatform-projects
        exclusiveContent {
            forRepository {
                ivy("https://nodejs.org/dist/") {
                    name = "Node Distributions at $url"
                    patternLayout { artifact("v[revision]/[artifact](-v[revision]-[classifier]).[ext]") }
                    metadataSources { artifact() }
                    content { includeModule("org.nodejs", "node") }
                }
            }
            filter { includeGroup("org.nodejs") }
        }
        exclusiveContent {
            forRepository {
                ivy("https://github.com/yarnpkg/yarn/releases/download") {
                    name = "Yarn Distributions at $url"
                    patternLayout { artifact("v[revision]/[artifact](-v[revision]).[ext]") }
                    metadataSources { artifact() }
                    content { includeModule("com.yarnpkg", "yarn") }
                }
            }
            filter { includeGroup("com.yarnpkg") }
        }
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
include(":data:source:local")
include(":data:source:remote:impl")
include(":data:model")
include(":data:db:sqlDelight")
include(":store:action")
include(":store:app")
include(":store:base")
include(":store:env")
include(":store:feature")
include(":store:reducer")
include(":store:state")
include(":util")
include(":data:source:local:contract")
include(":data:source:local:impl")
include(":desktop")
include(":shared-ui")
include(":web")
