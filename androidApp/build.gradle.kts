plugins {
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.android)

    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.android.application)
    alias(libs.plugins.ksp)
}

val signingConfigRelease = "release"
val signingConfigTestRelease = "test_release"

android {
    signingConfigs {
        create(signingConfigTestRelease) {
            storeFile = rootProject.file("keystore/android/test/keystore_test.jks")
            storePassword = "keystore_test_password"
            keyAlias = "key_test_alias"
            keyPassword = "key_test_password"
        }
    }

    namespace = GradleConfig.Android.NAMESPACE
    compileSdk = libs.versions.android.sdk.compile.get().toInt()

    defaultConfig {
        applicationId = GradleConfig.App.ID

        minSdk = libs.versions.android.sdk.min.get().toInt()
        targetSdk = libs.versions.android.sdk.target.get().toInt()

        versionCode = GradleConfig.App.VERSION_CODE
        versionName = GradleConfig.App.version.name

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
            isMinifyEnabled = true
            isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            resValue("string", "app_name", "Tmdb-Kmm")

            signingConfig = signingConfigs.getByName(signingConfigTestRelease)
        }

        debug {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"

            isShrinkResources = false
            isMinifyEnabled = false

            enableUnitTestCoverage = true

            resValue("string", "app_name", "Tmdb-Kmm-debug")
        }
    }

    lint {
        // https://developer.android.com/studio/write/lint
        baseline = file("lint-baseline.xml")
    }

    compileOptions {
        sourceCompatibility = GradleConfig.javaVersion
        targetCompatibility = GradleConfig.javaVersion
    }
}

dependencies {
    debugImplementation(libs.leakCanary.debug)

    implementation(libs.koin.android.worker)

    implementation(libs.androidx.splashscreen)
    implementation(libs.androidx.metrics)

    implementation(libs.logging.logcat)

    implementation(libs.compose.ui.tooling)
    implementation(libs.androidx.activity.compose)

    implementation(projects.composeApp)

}
