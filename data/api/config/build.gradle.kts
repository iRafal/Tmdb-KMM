import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.codingfeline.buildkonfig")
}

kotlin {
    android()
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "data.api.config"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.koin.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.koin.test)
            }
        }
        val androidMain by getting
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

val apiKey = properties["api.key"].toString()
val apiUrlBase = properties["api.url.base"].toString()
val apiUrlImage = properties["api.url.image"].toString()

buildkonfig {
    packageName = "${Versions.Android.BuildConfig.applicationId}.data.api.config"
    exposeObjectWithName = "DataApiConfigBuildKonfig"
    defaultConfigs {
        buildConfigField(STRING, "API_KEY", apiKey)
        buildConfigField(STRING, "API_BASE_URL", apiUrlBase)
        buildConfigField(STRING, "API_IMAGE_URL", apiUrlImage)
    }
}
android {
    namespace = "${Versions.Android.BuildConfig.applicationId}.data.api.config"
    compileSdk = Versions.Android.BuildConfig.compileSdk
    defaultConfig {
        minSdk = Versions.Android.BuildConfig.minSdk
    }
}