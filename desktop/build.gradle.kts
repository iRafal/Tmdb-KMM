import org.jetbrains.compose.desktop.application.dsl.TargetFormat

// https://github.com/JetBrains/compose-multiplatform/blob/master/tutorials/Getting_Started/README.md
// https://github.com/KiraResari/ceal-chronicler

plugins {
    kotlin(Plugins.Kotlin.multiplatform)

    @Suppress("DSL_SCOPE_VIOLATION")
    val composeMultiplatformVersion = libs.versions.compose.multiplatform.plugin
    id(Plugins.composeMultiplatrofm) version composeMultiplatformVersion
}

internal val packagePath = "${Versions.Android.BuildConfig.applicationId}.desktop"
group = packagePath
version = "1.0.0"

kotlin {
    jvm {
        withJava()
        compilations.all {
            kotlinOptions.jvmTarget = Versions.jvmTarget
        }
    }

    sourceSets {
        val jvmMain by getting {
            kotlin.srcDirs("src/jvmMain/kotlin/$packagePath")
            resources.srcDirs("src/jvmMain/resources")
            dependencies {
                implementation(libs.bundles.desktop)
                implementation(compose.desktop.currentOs)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.ui)
                implementation(compose.materialIconsExtended)

                implementation(project(":shared-ui"))
            }
        }
        val jvmTest by getting {
            dependencies {
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "$packagePath/TmdbMainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "TMDB"
            macOS {
                bundleID = packagePath
            }
        }
    }
}