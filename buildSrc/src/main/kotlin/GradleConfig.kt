import org.gradle.api.JavaVersion

object GradleConfig {

    object Android {
        const val compileSdk = 35
        const val namespace = "com.tmdb"
        const val minSdk = 29
        const val targetSdk = 35

        const val isMinifyEnabledDebug = false
        const val isMinifyEnabledRelease = true

        const val isShrinkResourcesDebug = false
        const val isShrinkResourcesRelease = true
    }

    object App {
        const val ID = "com.tmdb"
        val version = Version(major = 1, minor = 0, patch = 6)
        const val VERSION_CODE = 1
    }

    val javaVersion = JavaVersion.VERSION_17
    val javaVersionAsString = javaVersion.toString()

    object Plugins {
        const val BUILD_KONFIG = "com.codingfeline.buildkonfig"
        const val MOCK_MP = "org.kodein.mock.mockmp"
        const val SQL_DELIGHT = "app.cash.sqldelight"
    }
}
