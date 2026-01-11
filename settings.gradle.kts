enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

rootProject.name = "Tmdb-Kmm"
include(":androidApp")
include(":composeApp")
include(":store:action")
include(":store:base")
include(":store:env")
include(":store:feature")
include(":store:state")
include(":store:reducer")
include(":store:app")
include(":data:model")
include(":data:api:model")
include(":data:api:config")
include(":data:api:impl")
include(":data:db:sqlDelight")
include(":data:db:room")
include(":data:db:dataStore")
include(":data:db:multiplatform-settings")
include(":data:source:local:contract")
include(":data:source:local:impl")
include(":data:source:remote:contract")
include(":data:source:remote:impl")
include(":util")
