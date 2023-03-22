buildscript {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
    dependencies {
        classpath(libs.ktlint.plugin)
        classpath(libs.buildkonfig)
        classpath(libs.kotlin.serialization)
        classpath(libs.hilt.plugin)
    }
}

plugins {
    val gradle = "7.4.2"
    val kotlin = "1.8.0"
    id("com.android.application") version gradle apply false
    id("com.android.library") version gradle apply false
    kotlin("android") version kotlin apply false
    kotlin("multiplatform") version kotlin apply false
    id("org.jetbrains.kotlin.jvm") version kotlin apply false
}

/**
 * Mac
 * chmod +x gradlew (id needed, https://stackoverflow.com/questions/17668265/gradlew-permission-denied)
 * ./gradlew ktlintCheck
 * ./gradlew ktlintFormat
 *
 * Windows
 * gradlew ktlintCheck
 * gradlew ktlintFormat
 */

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint") // Version should be inherited from parent

    repositories {
        // Required to download KtLint
        mavenCentral()
    }

    // Optionally configure plugin
    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        debug.set(true)
        verbose.set(true)
        android.set(true)

        outputToConsole.set(true)
        outputColorName.set("RED")

        relative.set(true)
        ignoreFailures.set(false)
        enableExperimentalRules.set(false) // https://github.com/pinterest/ktlint/blob/master/ktlint-ruleset-experimental/src/main/kotlin/com/pinterest/ktlint/ruleset/experimental/ExperimentalRuleSetProvider.kt
        reporters {
            reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.HTML)
            reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
        }
        filter {
            exclude("**/generated/**")
            include("**/kotlin/**")
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}