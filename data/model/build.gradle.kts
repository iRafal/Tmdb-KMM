plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.multiplatform.android.library)
}

kotlin {
    val modulePath = "data.model"

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

    jvm ()
    val xcfName = "data:model:kit"
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
            implementation(libs.kotlin.stdlib)
            implementation(libs.koin.core)
            implementation(libs.kotlin.coroutines.core)
            implementation(libs.kotlinx.dateTime)

            implementation(projects.data.api.model)
            implementation(projects.data.api.config)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlinx.dateTime)
            implementation(libs.koin.test)
        }

        jvmMain.dependencies {
            implementation(libs.kotlin.coroutines.core)
        }
    }
}
