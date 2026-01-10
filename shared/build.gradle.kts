import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.multiplatform.hot.reload)
    alias(libs.plugins.compose.compiler)

    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    val jvm = JvmTarget.JVM_17

    androidTarget {
        compilerOptions {
            jvmTarget.set(jvm)
        }
    }

    jvm {
        compilerOptions {
            jvmTarget.set(jvm)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
            binaryOption("bundleId", "com.tmdb.shared")
        }
    }

    sourceSets {
        commonMain.dependencies {
            api(projects.store.app)
            api(projects.util)
            api(projects.data.db.dataStore)
            api(projects.data.db.multiplatformSettings)

            implementation(libs.logging.kermit)
            api(libs.kotlin.coroutines.core)
            api(libs.koin.core)
            api(libs.koin.compose)
            api(libs.koin.compose.viewModel)
            api(libs.kotlinx.dateTime)

            api(compose.foundation)
            api(compose.material3)
            api(compose.materialIconsExtended)
            api(compose.components.resources)
            api(compose.components.uiToolingPreview)
            api(compose.runtime)
            api(libs.androidx.lifecycle.viewmodel.multiplatform)
            api(libs.androidx.lifecycle.runtime.compose.multiplatform)
            api(libs.compose.navigation.multiplatform)

            api(libs.coil)
            api(libs.coil.compose)
            api(libs.coil.network)
            api(libs.coil.svg)

            api(libs.moko.resources)
            api(libs.moko.resources.compose)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.moko.resources.test)
        }
        androidMain.dependencies {
            api(compose.preview)

            api(libs.koin.android)
            api(libs.koin.android.compose)
            api(libs.koin.android.worker)

            api(libs.compose.ui.tooling)
            api(libs.androidx.activity.compose)

            api(libs.moko.permissions)
            api(libs.moko.permissions.compose)
            api(libs.moko.permissions.location)
            api(libs.moko.permissions.microphone)
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
        }
    }
}

android {
    namespace = "${GradleConfig.Android.namespace}.shared"
    compileSdk = GradleConfig.Android.compileSdk
    defaultConfig {
        minSdk = GradleConfig.Android.minSdk
    }
    compileOptions {
        sourceCompatibility = GradleConfig.javaVersion
        targetCompatibility = GradleConfig.javaVersion
    }
}
