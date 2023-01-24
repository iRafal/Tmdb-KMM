plugins {
    id("com.android.application")
    kotlin("android")
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
        kotlinCompilerExtensionVersion = "1.3.0"
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
    implementation("androidx.compose.ui:ui:1.3.3")
    implementation("androidx.compose.ui:ui-tooling:1.3.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.3.3")
    implementation("androidx.compose.foundation:foundation:1.3.1")
    implementation("androidx.compose.material:material:1.3.1")
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("androidx.core:core-ktx:+")
}