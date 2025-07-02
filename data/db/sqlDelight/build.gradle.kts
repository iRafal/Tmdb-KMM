import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.multiplatform.android.library)
    id(GradleConfig.Plugins.SQL_DELIGHT)
}

internal val packageNameValue = "${GradleConfig.Android.namespace}.data.db.sqldelight"

kotlin {
    androidLibrary {
        namespace = packageNameValue
        compileSdk = GradleConfig.Android.compileSdk
        minSdk = GradleConfig.Android.minSdk
    }

    jvm {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions.jvmTarget.set(JvmTarget.JVM_11)
            }
        }
    }

    val xcfName = "data.db.sqldelight.kit"
    iosX64 {
        binaries.framework {
            baseName = xcfName
            export("com.squareup.sqldelight:runtime")
        }
    }

    iosArm64 {
        binaries.framework {
            baseName = xcfName
            export("com.squareup.sqldelight:runtime")
        }
    }

    iosSimulatorArm64 {
        binaries.framework {
            baseName = xcfName
            export("com.squareup.sqldelight:runtime")
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
