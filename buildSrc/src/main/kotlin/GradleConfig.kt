import org.gradle.api.JavaVersion

object GradleConfig {

    object Android {
        const val NAMESPACE = "com.tmdb"
    }

    object App {
        const val ID = "com.tmdb"
        val version = Version(major = 1, minor = 0, patch = 6)
        const val VERSION_CODE = 1
    }

    val javaVersion = JavaVersion.VERSION_17
    val javaVersionAsString = javaVersion.toString()
}
