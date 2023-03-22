buildscript {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
    dependencies {
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

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}