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
        classpath(libs.moko.plugin.resources.generator)
    }
}

plugins {
    @Suppress("DSL_SCOPE_VIOLATION")
    val gradle = libs.versions.gradle

    @Suppress("DSL_SCOPE_VIOLATION")
    val kotlin = libs.versions.kotlin.asProvider()

    @Suppress("DSL_SCOPE_VIOLATION")
    val sqlDelight = libs.versions.sqlDelight

    id(Plugins.Android.application) version gradle apply false
    id(Plugins.Android.library) version gradle apply false
    kotlin(Plugins.Kotlin.android) version kotlin apply false
    kotlin(Plugins.Kotlin.multiplatform) version kotlin apply false
    kotlin("jvm") version kotlin apply false
    id(Plugins.sqlDelight) version sqlDelight apply false
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
    apply(plugin = Plugins.Kotlin.ktlint) // Version should be inherited from parent

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