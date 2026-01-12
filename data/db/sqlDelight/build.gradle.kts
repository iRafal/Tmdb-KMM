import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.multiplatform.android.library)
    alias(libs.plugins.sqldelight)
}


internal val modulePath = "data.db.sqldelight"
internal val packageNameValue = "${GradleConfig.Android.NAMESPACE}.$modulePath"

kotlin {

    androidLibrary {
        namespace = packageNameValue
        compileSdk = libs.versions.android.sdk.compile.get().toInt()
        minSdk = libs.versions.android.sdk.min.get().toInt()
    }

    jvm()

    @OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }

    val xcfName = "data:db:sqldelight:kit"
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
                api(libs.sqlDelight.extensions.coroutines)
                api(libs.sqlDelight.primitive.adapters)
                kotlin.srcDir("sqldelight")
            }
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlin.coroutines.test)
            implementation(libs.kotlinx.dateTime)
            implementation(libs.koin.test)
        }
        androidMain.dependencies {
            implementation(libs.sqlDelight.driver.android)
            implementation(libs.koin.android)
        }
        iosMain.dependencies {
            implementation(libs.sqlDelight.driver.native)
        }
        jvmMain.dependencies {
            implementation(libs.sqlDelight.driver.jvm)
        }
        jvmTest.dependencies {
            implementation(libs.sqlDelight.driver.jvm)
        }
        wasmJsMain.dependencies {
            // SQLDelight WebWorkerDriver not yet usable - browser APIs unavailable in Kotlin/Wasm
            // implementation(libs.sqlDelight.driver.web.worker)
        }
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
    linkSqlite = true
}
