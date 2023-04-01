plugins {
    kotlin(Plugins.Kotlin.multiplatform)
    id(Plugins.Android.library)

    @Suppress("DSL_SCOPE_VIOLATION")
    val composeMultiplatformVersion = libs.versions.compose.multiplatform.plugin
    id(Plugins.composeMultiplatrofm) version composeMultiplatformVersion

    id(Plugins.mokoResources)
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
            baseName = "shared-ui"
            export(libs.moko.resources)
            export(libs.moko.graphics) // toUIColor here
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":shared"))
                api(compose.ui)
                api(compose.foundation)
                api(compose.material)
                api(compose.runtime)
                api(compose.materialIconsExtended)
                implementation(libs.kotlinx.dateTime)
                api(libs.image.loader.kmm)
//                api(libs.image.loader.kmm.extension.blur)

                api(libs.moko.resources)
                api(libs.moko.resources.compose)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.moko.resources.test)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.compose.runtime)
                implementation(libs.compose.ui)
                implementation(libs.compose.ui.preview)
                implementation(libs.coil.compose)
            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependencies {
                api(libs.image.loader.kmm)
            }
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
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
            dependencies {
                implementation(libs.kotlinx.dateTime)
                implementation(compose.uiTooling)
                api(libs.image.loader.kmm.extension.imageio)
            }
        }
        val jvmTest by getting
    }
}

internal val packagePath = "${Config.rootPackage}.shared_ui"

android {
    namespace = packagePath
    compileSdk = Versions.Android.BuildConfig.compileSdk
    defaultConfig {
        minSdk = Versions.Android.BuildConfig.minSdk
    }
}

multiplatformResources {
    multiplatformResourcesPackage = packagePath
    multiplatformResourcesClassName = "MR"
    multiplatformResourcesVisibility = dev.icerock.gradle.MRVisibility.Public
    iosBaseLocalizationRegion = "en"
    multiplatformResourcesSourceSet = "commonMain"
}