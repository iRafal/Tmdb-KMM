import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.multiplatform.android.library)
}

kotlin {
    androidLibrary {
        namespace = "${GradleConfig.Android.namespace}.store.reducer"
        compileSdk = GradleConfig.Android.compileSdk
        minSdk = GradleConfig.Android.minSdk

        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }
    }

    jvm {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
            }
        }
    }

    val xcfName = "store.reducer.kit"

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
            implementation(libs.kotlin.stdlib)
            implementation(libs.koin.core)
            implementation(libs.kotlin.coroutines.core)

            implementation(projects.store.base)
            implementation(projects.store.env)
            implementation(projects.store.action)
            implementation(projects.store.feature)
            implementation(projects.store.state)
            implementation(projects.data.source.remote.contract)
            implementation(projects.data.source.local.contract)
            api(projects.data.model)
            implementation(projects.util)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.koin.test)
            implementation(libs.kotlin.coroutines.test)
            implementation(libs.kotlinx.dateTime)
        }
    }
}
