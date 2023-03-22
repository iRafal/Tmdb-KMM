plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.tmdb.android"
    compileSdk = Versions.Android.BuildConfig.compileSdk
    defaultConfig {
        applicationId = "com.tmdb.android"
        minSdk = Versions.Android.BuildConfig.minSdk
        targetSdk = Versions.Android.BuildConfig.targetSdk
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlin.compiler.extension.get()
    }
    packagingOptions {
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
    implementation(project(":shared"))
    implementation(libs.bundles.app)
    kapt(libs.bundles.app.kapt)
    kaptTest(libs.bundles.app.kapt.test)
    kaptAndroidTest(libs.bundles.app.kapt.test.android)
}