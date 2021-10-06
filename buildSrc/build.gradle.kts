import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    // Local files
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Gradle Api
    compileOnly(gradleApi())

    // Android gradle plugin
    implementation("com.android.tools.build:gradle:4.2.2")

    // Kotlin gradle plugin
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.20")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

gradlePlugin {
    plugins.register("Kotlin-Library-Module") {
        id = "com.tailored.gradle.kotlin-library"
        implementationClass = "com.tailored.gradle.KotlinLibraryModulePlugin"
    }

    plugins.register("Android-Library-Module") {
        id = "com.tailored.gradle.android-library"
        implementationClass = "com.tailored.gradle.AndroidLibraryModulePlugin"
    }

    plugins.register("Android-App-Module") {
        id = "com.tailored.gradle.android-app"
        implementationClass = "com.tailored.gradle.AndroidAppModulePlugin"
    }
}