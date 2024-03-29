plugins {
    kotlin(Plugins.Kotlin.multiplatform)
    id(Plugins.Android.library)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions.jvmTarget = Versions.jvmTargetAsString
        }
    }

    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = Versions.jvmTargetAsString
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "store.reducer"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlin.coroutines.core)
                implementation(libs.koin.core)
                implementation(project(":store:base"))
                implementation(project(":store:env"))
                implementation(project(":store:action"))
                implementation(project(":store:feature"))
                implementation(project(":store:state"))
                implementation(project(":data:source:remote:contract"))
                implementation(project(":data:source:local:contract"))
                api(project(":data:model"))
                implementation(project(":util"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.koin.test)
                implementation(libs.kotlin.coroutines.test)
                implementation(libs.kotlinx.dateTime)
                implementation(kotlin("test"))
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

android {
    namespace = "${Config.rootPackage}.store.reducer"
    compileSdk = Versions.Android.BuildConfig.compileSdk
    defaultConfig {
        minSdk = Versions.Android.BuildConfig.minSdk
        compileOptions {
            sourceCompatibility = Versions.jvmTarget
            targetCompatibility = Versions.jvmTarget
        }
    }
}