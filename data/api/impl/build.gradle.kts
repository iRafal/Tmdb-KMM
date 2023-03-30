plugins {
    kotlin(Plugins.Kotlin.multiplatform)
    id(Plugins.Android.library)
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = Versions.jvmTarget
            }
        }
    }

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
            baseName = "data.api.impl"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.bundles.data.api.impl.commonMain)
                implementation(project(":data:api:config"))
                implementation(project(":data:api:model"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.koin.test)
                implementation(libs.kotlin.coroutines.test)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.client.android)
                implementation(libs.ktor.client.okhttp)
            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(libs.ktor.client.darwin)
                implementation(libs.ktor.client.ios)
            }
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
    namespace = "${Versions.Android.BuildConfig.applicationId}.data.api.impl"
    compileSdk = Versions.Android.BuildConfig.compileSdk
    defaultConfig {
        minSdk = Versions.Android.BuildConfig.minSdk
    }
}