import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.multiplatform.hot.reload)
}

internal val packagePath = "${GradleConfig.Android.namespace}.desktop"
group = packagePath
version = GradleConfig.App.version.name

kotlin {
    jvm {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_11)
                }
            }
        }
    }

    sourceSets {
        jvmMain {
            kotlin.srcDirs("src/jvmMain/kotlin/$packagePath")
            resources.srcDirs("src/jvmMain/resources")
            dependencies {
                implementation(projects.shared)

                implementation(libs.koin.core)
                implementation(libs.kotlinx.dateTime)

                implementation(compose.desktop.currentOs)

                implementation(libs.ktor.client.cio)
                implementation(libs.ktor.client.core)
            }
        }
        jvmTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "$packagePath/TmdbMainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "TMDB"
            macOS {
                bundleID = packagePath
            }
            linux {
                this.packageName = "TMDB"
            }
            windows {
                shortcut = true
                menu = true
            }
        }
    }
}
