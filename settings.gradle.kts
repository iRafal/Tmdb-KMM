enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Tmdb-Kmm"
include(":androidApp")
include(":desktopApp")
include(":shared")
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
include(":data:db:dataStore")
include(":data:db:multiplatform-settings")
include(":data:source:local:contract")
include(":data:source:local:impl")
include(":data:source:remote:contract")
include(":data:source:remote:impl")
include(":util")
