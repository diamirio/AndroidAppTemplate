plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    // Local files
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Gradle Api
    compileOnly(gradleApi())

    // Android gradle plugin
    compileOnly("com.android.tools.build:gradle:4.2.2")

    // Kotlin gradle plugin
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.30")
}

gradlePlugin {
    plugins.register("Kotlin-Library-Module") {
        id = "com.tailored.gradle.kotlin-library"
        implementationClass = "com.tailored.build.KotlinLibraryModulePlugin"
    }

    plugins.register("Android-Library-Module") {
        id = "com.tailored.gradle.android-library"
        implementationClass = "com.tailored.build.AndroidLibraryModulePlugin"
    }

    plugins.register("Android-App-Module") {
        id = "com.tailored.gradle.android-app"
        implementationClass = "com.tailored.build.AndroidAppModulePlugin"
    }
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}