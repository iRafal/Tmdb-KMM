plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.multiplatform.android.library)
}

kotlin {
    val modulePath = "store.app"

    androidLibrary {
        namespace = "${GradleConfig.Android.NAMESPACE}.$modulePath"
        compileSdk = libs.versions.android.sdk.compile.get().toInt()
        minSdk = libs.versions.android.sdk.min.get().toInt()
    }

    jvm ()

    val xcfName = "store:app:kit"
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
            implementation(libs.kotlin.stdlib)
            implementation(libs.koin.core)
            implementation(libs.kotlin.coroutines.core)
            api(projects.store.base)
            api(projects.store.env)
            api(projects.store.state)
            api(projects.store.reducer)
            api(projects.store.feature)
            api(projects.store.action)
            implementation(projects.util)
        }
    }
}
