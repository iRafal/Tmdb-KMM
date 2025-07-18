import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.multiplatform.android.library)
}

kotlin {
    androidLibrary {
        namespace = "${GradleConfig.Android.namespace}.data.source.local.impl"
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

    val xcfName = "data.source.local.impl.kit"

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = xcfName
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlin.coroutines.core)
            implementation(libs.koin.core)
            implementation(libs.kotlinx.dateTime)
            implementation(projects.data.source.local.contract)
            implementation(projects.data.db.sqlDelight)
            implementation(projects.util)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}
