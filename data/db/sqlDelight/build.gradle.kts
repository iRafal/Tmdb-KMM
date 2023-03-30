plugins {
    kotlin(Plugins.Kotlin.multiplatform)
    id(Plugins.Android.library)
    id(Plugins.sqlDelight)
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
            baseName = "sqlDelight"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.koin.core)
                implementation(libs.kotlinx.dateTime)
                api(libs.sqlDelight.extensions.coroutines)
                api(libs.sqlDelight.primitive.adapters)
                kotlin.srcDir("sqldelight")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.kotlin.coroutines.test)
                implementation(libs.kotlinx.dateTime)
                implementation(libs.koin.test)
            }
        }
        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(libs.sqlDelight.driver.android)
                implementation(libs.koin.android)
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
                implementation(libs.sqlDelight.driver.native)
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
        val jvmMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(libs.sqlDelight.driver.jvm)
            }
        }
        val jvmTest by getting {
            dependsOn(commonTest)
            dependencies {
                implementation(libs.sqlDelight.driver.jvm)
            }
        }
    }
}

internal val packageNameValue = "${Versions.Android.BuildConfig.applicationId}.data.db.sqldelight"

android {
    namespace = packageNameValue
    compileSdk = Versions.Android.BuildConfig.compileSdk
    defaultConfig {
        minSdk = Versions.Android.BuildConfig.minSdk
    }
}

kotlin {
    jvm()
}

sqldelight {
    databases {
        create("MovieDb") {
            schemaOutputDirectory.set(file("src/commonMain/sqldelight/com/tmdb/data/db/sqlDelight/schemas"))
            verifyMigrations.set(true)
            packageName.set(packageNameValue)
        }
    }
}
