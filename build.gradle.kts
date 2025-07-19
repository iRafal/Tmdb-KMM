buildscript {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
    dependencies {
        classpath(libs.buildKonfig)
        classpath(libs.moko.plugin.resources.generator)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false

    alias(libs.plugins.compose.multiplatform) apply false
    alias(libs.plugins.compose.multiplatform.hot.reload) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.ksp) apply false

    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.multiplatform.android.library) apply false

    alias(libs.plugins.mock.mp) apply false

    alias(libs.plugins.room) apply false

    @Suppress("DSL_SCOPE_VIOLATION")
    val sqlDelight = libs.versions.sqlDelight
    id(GradleConfig.Plugins.SQL_DELIGHT) version sqlDelight apply false
}
