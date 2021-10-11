import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

dependencies {
    // Kotlin gradle plugin
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")

    // Android gradle plugin
    implementation("com.android.tools.build:gradle:7.0.2")
}