plugins {
    id(Plugins.Android.application)
    kotlin(Plugins.Kotlin.android)
    kotlin(Plugins.Kotlin.kapt)
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "${Config.rootPackage}.android"
    compileSdk = Versions.Android.BuildConfig.compileSdk
    defaultConfig {
        applicationId = "${Config.rootPackage}.android"
        minSdk = Versions.Android.BuildConfig.minSdk
        targetSdk = Versions.Android.BuildConfig.targetSdk
        versionCode = 1
        versionName = "1.0.0"

        compileOptions {
            sourceCompatibility = Versions.jvmTarget
            targetCompatibility = Versions.jvmTarget
        }
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlin.compiler.extension.get()
    }
    kotlinOptions {
        jvmTarget = Versions.jvmTargetAsString
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"

            isShrinkResources = Versions.Android.BuildConfig.isShrinkResourcesDebug
            isMinifyEnabled = Versions.Android.BuildConfig.isMinifyEnabledDebug
        }
        getByName("release") {
            isShrinkResources = Versions.Android.BuildConfig.isShrinkResourcesRelease
            isMinifyEnabled = Versions.Android.BuildConfig.isMinifyEnabledRelease
        }
    }
}

dependencies {
    implementation(project(":shared-ui"))
    implementation(libs.bundles.app)
    kapt(libs.bundles.app.kapt)
    kaptTest(libs.bundles.app.kapt.test)
    kaptAndroidTest(libs.bundles.app.kapt.test.android)
}