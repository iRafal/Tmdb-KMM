plugins {
    kotlin(Plugins.Kotlin.multiplatform)

    @Suppress("DSL_SCOPE_VIOLATION")
    val composeMultiplatformVersion = libs.versions.compose.multiplatform.plugin
    id(Plugins.composeMultiplatrofm) version composeMultiplatformVersion
}

kotlin {
    js(IR) {
        browser {
            testTask {
                testLogging.showStandardStreams = true
                useKarma {
                    useChromeHeadless()
                    useFirefox()
                }
            }
        }
        binaries.executable()
    }
    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(compose.web.core)
                implementation(compose.runtime)
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}