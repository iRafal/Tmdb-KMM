import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.multiplatform.android.library)
    alias(libs.plugins.buildKonfig)
}

kotlin {
    val modulePath = "data.api.config"

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

    val xcfName = "data:api:config:kit"
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
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.koin.test)
        }

        jvmMain.dependencies {
            implementation(libs.kotlin.coroutines.core)
        }
    }
}

val apiKey = properties["api.key"].toString()
val apiUrlBase = properties["api.url.base"].toString()
val apiUrlImage = properties["api.url.image"].toString()

buildkonfig {
    packageName = "${GradleConfig.Android.NAMESPACE}.data.api.config"
    exposeObjectWithName = "DataApiConfigBuildKonfig"
    defaultConfigs {
        buildConfigField(STRING, "API_KEY", apiKey)
        buildConfigField(STRING, "API_BASE_URL", apiUrlBase)
        buildConfigField(STRING, "API_IMAGE_URL", apiUrlImage)
    }
}
