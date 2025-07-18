import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.multiplatform.android.library)
    id(GradleConfig.Plugins.BUILD_KONFIG)
}

kotlin {
    androidLibrary {
        namespace = "${GradleConfig.Android.namespace}.data.api.config"
        compileSdk = GradleConfig.Android.compileSdk
        minSdk = GradleConfig.Android.minSdk

        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    jvm {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
            }
        }
    }

    val xcfName = "data.api.config.kit"
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = xcfName
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
    packageName = "${GradleConfig.Android.namespace}.data.api.config"
    exposeObjectWithName = "DataApiConfigBuildKonfig"
    defaultConfigs {
        buildConfigField(STRING, "API_KEY", apiKey)
        buildConfigField(STRING, "API_BASE_URL", apiUrlBase)
        buildConfigField(STRING, "API_IMAGE_URL", apiUrlImage)
    }
}
