plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.multiplatform.hot.reload)
}

val signingConfigTestRelease = "test_release"

android {
    signingConfigs {
        create(signingConfigTestRelease) {
            storeFile = file("../keystore/test/keystore_test.jks")
            storePassword = "keystore_test_password"
            keyAlias = "key_test_alias"
            keyPassword = "key_test_password"
        }
    }

    // More details: https://developer.android.com/guide/topics/resources/app-languages
//    androidResources.generateLocaleConfig = true

    namespace = GradleConfig.Android.namespace
    compileSdk = GradleConfig.Android.compileSdk

    defaultConfig {
        applicationId = GradleConfig.Android.applicationId
        minSdk = GradleConfig.Android.minSdk
        targetSdk = GradleConfig.Android.targetSdk

        versionCode = GradleConfig.App.VERSION_CODE
        versionName = GradleConfig.App.version.name

        vectorDrawables.useSupportLibrary = true

        setProperty("archivesBaseName", "$applicationId-$versionName-$versionCode")

        bundle.language.enableSplit = false
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
       release {
            isMinifyEnabled = GradleConfig.Android.isMinifyEnabledRelease
            isShrinkResources = GradleConfig.Android.isShrinkResourcesRelease

           proguardFiles(
               getDefaultProguardFile("proguard-android-optimize.txt"),
               "proguard-rules.pro"
           )

           resValue("string", "app_name", "TmdbKmm")

           signingConfig = signingConfigs.getByName(signingConfigTestRelease)
        }
        debug {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"

            isShrinkResources = GradleConfig.Android.isShrinkResourcesDebug
            isMinifyEnabled = GradleConfig.Android.isMinifyEnabledDebug

            enableUnitTestCoverage = true

            resValue("string", "app_name", "TmdbKmm-Debug")
        }
    }
    compileOptions {
        sourceCompatibility = GradleConfig.javaVersion
        targetCompatibility = GradleConfig.javaVersion
    }
    kotlinOptions {
        jvmTarget = GradleConfig.javaVersionAsString
    }
}

dependencies {
    implementation(projects.shared)

    implementation(libs.androidx.work.runtime)
    implementation(libs.logging.logcat)

    debugImplementation(libs.compose.ui.tooling)
}
