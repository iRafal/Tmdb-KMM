import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.multiplatform.android.library)
    alias(libs.plugins.ksp)

    @Suppress("DSL_SCOPE_VIOLATION")
    id(GradleConfig.Plugins.MOCK_MP) version libs.versions.mockmp
}

kotlin {
    androidLibrary {
        namespace = "${GradleConfig.Android.namespace}.data.source.remote.impl"
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
            }
            kotlin.srcDir("build/generated/ksp")
        }
    }
}
