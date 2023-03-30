import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING

plugins {
    kotlin(Plugins.Kotlin.multiplatform)
    id(Plugins.Android.library)
    id(Plugins.buildkonfig)
}

kotlin {
    android()

    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = Versions.jvmTarget
        }
    }
    
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