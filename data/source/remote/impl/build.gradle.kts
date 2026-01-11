plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.multiplatform.android.library)
    alias(libs.plugins.ksp)
}

kotlin {
    val modulePath = "data.source.remote.impl"

    androidLibrary {
        namespace = "${GradleConfig.Android.NAMESPACE}.$modulePath"
        compileSdk = libs.versions.android.sdk.compile.get().toInt()
        minSdk = libs.versions.android.sdk.min.get().toInt()
    }

    jvm ()

    val xcfName = "data:source:remote:impl:kit"
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
        commonMain.dependencies {
            implementation(libs.koin.core)
            implementation(libs.kotlinx.dateTime)
            implementation(libs.logging.kermit)
            implementation(projects.data.source.remote.contract)
            implementation(projects.data.api.impl)
        }
        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
                implementation(libs.koin.test)
                implementation(libs.kotlinx.dateTime)
                implementation(libs.kotlin.coroutines.test)
            }
            kotlin.srcDir("build/generated/ksp")
        }
    }
}
