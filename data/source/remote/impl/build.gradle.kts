import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.multiplatform.android.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.mock.mp)
}

kotlin {
    androidLibrary {
        namespace = "${GradleConfig.Android.NAMESPACE}.data.source.remote.impl"
        compileSdk = libs.versions.android.sdk.compile.get().toInt()
        minSdk = libs.versions.android.sdk.min.get().toInt()
    }

    jvm {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
            }
        }
    }

    val xcfName = "data.source.remote.impl.kit"

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
                implementation(libs.mock.mp.runtime)
            }
            kotlin.srcDir("build/generated/ksp")
        }
    }
}
