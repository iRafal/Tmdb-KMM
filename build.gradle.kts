import com.android.build.gradle.BaseExtension
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

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

    alias(libs.plugins.room) apply false

    @Suppress("DSL_SCOPE_VIOLATION")
    val sqlDelight = libs.versions.sqlDelight
    id(GradleConfig.Plugins.SQL_DELIGHT) version sqlDelight apply false

    /*
    * ./gradlew detekt
    * ./gradlew detektBaseline - prefer using this one
    */
    alias(libs.plugins.detekt) apply true

    alias(libs.plugins.ktlint) apply true

    alias(libs.plugins.kotlinx.kover)

    alias(libs.plugins.buildKonfig) apply false

    alias(libs.plugins.google.services) apply false
}


val codeCoverageExcludeList = listOf(
    // Project specific UI exclusions
    "**/ui/components/*",
    "**/*UiTags.*",
    "**/*Screen*.*",
    "**/*ScreenUi*.*",
    "**/*ScaffoldContent*.*",
    "**/*UiPreview*.*",
    "**/*Ui*Preview*.*",
    "**/*Event.*",
    "**/*Module.*",
    "**/*PreviewData*.*", // `app`, ui-core module(s) classes
    "**/*UiData*.*", // `app`, ui-core module(s) Ui Data Model classes
    "**/*DataModel*.*", // `data` module DataModel classes
    // "**/*ApiModel.*", // `data/api/model` module ApiModel classes

    // Project specific common exclusions
    "**/RootUtils.kt",
    "**/*WorkerService.*",
    "**/di/*",

    // Common exclusions
    "**/BuildConfig.*",
    "**/*Test*.*",
    "**/R.class",
    "**/R$*.class",
    "**/*$*",
    "**/Manifest*.*",
    "android/**/*.*",
    "**/*Dagger*.*",
    "**/Dagger*Component*.*",
    "**/*_Factory*.*",
    "**/*_Provide*Factory*.*",
    "**/*Companion*.*",
    "**/*MembersInjector*.*",
    "**/*_MembersInjector.class",
    "**/*Component*.*",
    "**/*Extensions*.*",
    "**/Lambda$*.class",
    "**/*\$Lambda$*.*",
    "**/Lambda.class",
    "**/*Lambda.class",
    "**/*Lambda*.class",
    "**/*Module_*Factory.class",

    // Kotlin specific exclusions
    "**/*\$Properties*", // Kotlin properties
    "**/*\$Companion*",
    "**/BuildConfig*",
    "**/*\$Initializer*",
    "**/*\$Creator*" // Parcelable creators
)

kover {
    reports {
        total {
            xml {
                onCheck = false
            }
            html {
                onCheck = true
            }
        }
        filters {
            excludes {
                androidGeneratedClasses()
                classes(codeCoverageExcludeList)
            }
        }
    }
}

/*
 * Lint terminal https://developer.android.com/studio/write/lint
 * ./gradlew lint
 * ./gradlew lintDebug
 * ./gradlew lintRelease
 */

/*
 * Run all tests
 * https://developer.android.com/studio/test/command-line
 *
 * ./gradlew test
 *
 * ./gradlew connectedAndroidTest
 * ./gradlew cAT
 */

/*
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
    apply {
        plugin(rootProject.libs.plugins.ktlint.get().pluginId) // Version should be inherited from parent
    }

    plugins.withType<org.jetbrains.kotlin.gradle.plugin.KotlinBasePluginWrapper> {
        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_17)
                freeCompilerArgs.add("-Xcontext-receivers")
            }
        }
    }

//    plugins.withType<com.android.build.gradle.BasePlugin> {
//        extensions.configure<BaseExtension> {
//            compileOptions {
//                sourceCompatibility = GradleConfig.javaVersion
//                targetCompatibility = GradleConfig.javaVersion
//            }
//        }
//    }

    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        verbose.set(true)
        android.set(true)

        outputToConsole.set(true)
        outputColorName.set("RED")

        relative.set(true)
        ignoreFailures.set(false)
        enableExperimentalRules.set(true)
        reporters {
            reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.HTML)
            reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
        }
        filter {
            exclude("**/generated/**")
            exclude("**/resources/Res.kt")
            exclude("**/compose/resourceGenerator/**")
            exclude("**/*generated*/**")
            exclude("**/composeResources/**/*generated*/**")
            exclude { element ->
                element.file.path.contains("build") ||
                    element.file.path.contains("generated") ||
                    element.file.name.contains("Generated") ||
                    element.file.path.contains("compose/resourceGenerator") ||
                    element.file.path.contains("composeResources")
            }
            include("**/kotlin/**")
        }
    }

    // Disable ktlint for generated compose files by excluding problematic tasks
    tasks.matching { task ->
        task.name.contains("ktlint") && task.name.contains("SourceSet")
    }.configureEach {
        enabled = false
    }
}

// https://detekt.dev/docs/gettingstarted/gradle#kotlin-dsl-3
tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    config.setFrom(file("config/detekt/detekt.yml"))
    baseline.set(file("${rootProject.projectDir}/config/detekt/baseline.xml"))
    include("**/*.kt")
    exclude("**/build/**")
    setSource(projectDir)
    allRules = false
    parallel = false
    disableDefaultRuleSets = false
    buildUponDefaultConfig = true
    debug = false
    ignoreFailures = false
    basePath = projectDir.absolutePath
    reports {
        xml.required.set(true)
        html.required.set(true)
        txt.required.set(true)
        sarif.required.set(true)
        md.required.set(true)
    }
    jvmTarget = GradleConfig.javaVersionAsString
}


/*
 * https://detekt.dev/docs/introduction/baseline/
 */
tasks.withType<io.gitlab.arturbosch.detekt.DetektCreateBaselineTask>().configureEach {
    jvmTarget = GradleConfig.javaVersionAsString
    description = "Overrides current baseline."
    buildUponDefaultConfig.set(true)
    ignoreFailures.set(true)
    parallel.set(true)
    setSource(files(rootDir))
    config.setFrom(files("config/detekt/detekt.yml"))
    baseline.set(file("config/detekt/baseline.xml"))
    include("**/*.kt")
    include("**/*.kts")
    exclude("**/resources/**")
    exclude("**/build/**")
}
