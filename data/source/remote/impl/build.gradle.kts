plugins {
    kotlin(Plugins.Kotlin.multiplatform)
    id(Plugins.Android.library)

    @Suppress("DSL_SCOPE_VIOLATION")
    id(Plugins.mockMp) version libs.versions.mockmp
}

kotlin {
    android()
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "data.source.remote.impl"
        }
    }

    sourceSets {

        val commonMain by getting {
            dependencies {
                implementation(libs.koin.core)
                implementation(libs.kotlinx.dateTime)
                implementation(libs.logging.kermit)
                implementation(project(":data:source:remote:contract"))
                implementation(project(":data:api:impl"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.koin.test)
                implementation(libs.kotlinx.dateTime)
                implementation(libs.kotlin.coroutines.test)
                implementation(kotlin("test"))
            }
            kotlin.srcDir("build/generated/ksp")
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

android {
    namespace = "${Versions.Android.BuildConfig.applicationId}.data.source.remote.impl"
    compileSdk = Versions.Android.BuildConfig.compileSdk
    defaultConfig {
        minSdk = Versions.Android.BuildConfig.minSdk
    }
}

mockmp {
    usesHelper = true
    public = false
}