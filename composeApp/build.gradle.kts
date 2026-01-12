import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.multiplatform.hot.reload)
    alias(libs.plugins.compose.compiler)

    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.multiplatform.android.library)
    alias(libs.plugins.kotlin.serialization)
}

compose.resources {
    publicResClass = true
    packageOfResClass = "${GradleConfig.Android.NAMESPACE}.resources"
    generateResClass = always
}

kotlin {
    androidLibrary {
        namespace = GradleConfig.Android.NAMESPACE
        compileSdk = libs.versions.android.sdk.compile.get().toInt()

        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }

        experimentalProperties["android.experimental.kmp.enableAndroidResources"] = true

        lint {
            // https://developer.android.com/studio/write/lint
            baseline = file("lint-baseline.xml")
        }
    }

    jvm()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
            // if (iosTarget == iosArm64()) optimized = true
            binaryOption("bundleId", GradleConfig.App.ID)
        }
    }
    sourceSets {
        androidMain.dependencies {
            implementation(libs.koin.android.worker)

            api(libs.androidx.splashscreen)
            api(libs.androidx.metrics)

            api(compose.preview)

            api(libs.logging.logcat)

            implementation(libs.koin.android)
            implementation(libs.koin.android.compose)
            implementation(libs.koin.android.worker)

            api(libs.compose.ui.tooling)
            api(libs.androidx.activity.compose)

            implementation(libs.moko.permissions)
            implementation(libs.moko.permissions.compose)
            implementation(libs.moko.permissions.location)
            implementation(libs.moko.permissions.microphone)
        }

        commonMain.dependencies {
            implementation(projects.store.app)
            implementation(projects.util)
            implementation(projects.data.db.dataStore)
            implementation(projects.data.db.multiplatformSettings)

            implementation(libs.logging.kermit)
            implementation(libs.kotlin.coroutines.core)
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewModel)
            implementation(libs.kotlinx.dateTime)

            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(compose.runtime)
            implementation(libs.androidx.lifecycle.viewmodel.multiplatform)
            implementation(libs.androidx.lifecycle.runtime.compose.multiplatform)
            implementation(libs.compose.navigation.multiplatform)

            api(libs.coil)
            api(libs.coil.compose)
            implementation(libs.coil.network)
            implementation(libs.coil.svg)

            implementation(libs.moko.resources)
            implementation(libs.moko.resources.compose)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.moko.resources.test)
        }

        iosMain.dependencies {
            api(libs.moko.permissions)
            api(libs.moko.permissions.compose)
            api(libs.moko.permissions.location)
            api(libs.moko.permissions.microphone)
        }

        jvmMain.dependencies {

            api(compose.uiTooling)
            api(libs.kotlin.coroutines.swing)
            implementation(compose.desktop.currentOs)
            implementation(libs.ktor.client.cio)
            implementation(libs.ktor.client.core)
        }

        jvmTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

internal val packagePath = GradleConfig.Android.NAMESPACE
group = packagePath
version = GradleConfig.App.version.name

compose.desktop {
    application {
        mainClass = "$packagePath/TmdbMainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "TMDB"
            macOS {
                bundleID = GradleConfig.App.ID
            }
            linux {
                this.packageName = "TMDB"
            }
            windows {
                shortcut = true
                menu = true
            }
        }
    }
}
