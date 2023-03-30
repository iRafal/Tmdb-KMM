import org.gradle.api.JavaVersion

object Versions {
    object Android {
        object BuildConfig {
            const val compileSdk = 33
            const val minSdk = 26
            const val targetSdk = 33

            const val applicationId = "com.tmdb"

            const val isMinifyEnabledDebug = false
            const val isMinifyEnabledRelease = false

            const val isShrinkResourcesDebug = false
            const val isShrinkResourcesRelease = false
        }
    }

    val jvmTarget = JavaVersion.VERSION_11.toString()
}