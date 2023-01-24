buildscript {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
    dependencies {
        classpath(libs.buildkonfig)
        classpath(libs.kotlin.serialization)
    }
}

plugins {
    val gradle = "7.4.0"
    val kotlin = "1.7.10"
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application") version gradle apply false
    id("com.android.library") version gradle apply false
    kotlin("android") version kotlin apply false
    kotlin("multiplatform") version kotlin apply false
    id("org.jetbrains.kotlin.jvm") version kotlin apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}