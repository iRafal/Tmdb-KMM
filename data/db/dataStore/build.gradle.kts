plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.multiplatform.android.library)
}

kotlin {
    val modulePath = "data.db.dataStore"

    androidLibrary {
        namespace = "${GradleConfig.Android.NAMESPACE}.data.db.dataStore"
        compileSdk = libs.versions.android.sdk.compile.get().toInt()
        minSdk = libs.versions.android.sdk.min.get().toInt()
    }

    jvm()

    @OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }

    val xcfName = "data:db:dataStore:kit"
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
        commonMain {
            dependencies {
                implementation(libs.koin.core)
                implementation(libs.kotlinx.dateTime)
                implementation(libs.kotlin.coroutines.core)
            }
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlin.coroutines.test)
            implementation(libs.kotlinx.dateTime)
            implementation(libs.koin.test)
        }
        androidMain.dependencies {
            implementation(libs.koin.android)
            api(libs.data.store.preferences)
            api(libs.data.store)
        }
        iosMain.dependencies {
            api(libs.data.store.preferences)
            api(libs.data.store)
        }
        jvmMain.dependencies {
            api(libs.data.store.preferences)
            api(libs.data.store)
        }
        jvmTest.dependencies {
        }
    }
}
