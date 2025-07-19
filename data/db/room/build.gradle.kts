import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.multiplatform.android.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
}

internal val packageNameValue = "${GradleConfig.Android.namespace}.data.db.room"

kotlin {
    androidLibrary {
        namespace = packageNameValue
        compileSdk = GradleConfig.Android.compileSdk
        minSdk = GradleConfig.Android.minSdk
    }

    jvm {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
            }
        }
    }

    val xcfName = "data.db.room.kit"

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = xcfName
            isStatic = true
            linkerOpts.add("-lsqlite3")
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.util)

                implementation(libs.koin.core)
                implementation(libs.kotlinx.dateTime)

                implementation(libs.room.runtime)
                implementation(libs.sqlite.bundled)
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
            implementation(libs.room.ktx)
        }
        iosMain.dependencies {
        }
        jvmMain.dependencies {
        }
        jvmTest.dependencies {
        }
    }
}

kotlin {
    jvm()
}

room {
    schemaDirectory("$projectDir/schemas")
}
