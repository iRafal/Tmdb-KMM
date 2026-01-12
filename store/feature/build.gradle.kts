plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.multiplatform.android.library)
}

kotlin {
    val modulePath = "store.feature"

    androidLibrary {
        namespace = "${GradleConfig.Android.NAMESPACE}.$modulePath"
        compileSdk = libs.versions.android.sdk.compile.get().toInt()
        minSdk = libs.versions.android.sdk.min.get().toInt()

        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    jvm()

    @OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }

    val xcfName = "store:feature:kit"
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = xcfName
            binaryOption("bundleId", "${GradleConfig.App.ID}.$modulePath")
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.store.base)
        }
    }
}
