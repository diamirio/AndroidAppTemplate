plugins {
    id("org.jlleitschuh.gradle.ktlint") version "10.2.0"
    id("com.akaita.android.easylauncher") version "1.3.1" apply false
    id("androidx.navigation.safeargs.kotlin") version "2.3.5" apply false
    id("org.jetbrains.kotlin.jvm") version "1.5.30" apply false
    id("com.android.application") version "4.2.2" apply false
}

allprojects {
    repositories {
        maven { url = uri("https://maven.tailored-apps.com/repository/proxy-google/") }
        maven { url = uri("https://maven.tailored-apps.com/repository/proxy-maven-central/") }
        maven { url = uri("https://maven.tailored-apps.com/repository/proxy-jcenter/") }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }

    extensions.findByType<com.android.build.gradle.AppExtension>()?.apply {
        defaultConfig {
            compileOptions {
                targetCompatibility = JavaVersion.VERSION_1_8
                sourceCompatibility = JavaVersion.VERSION_1_8
            }
        }
    }

    extensions.findByType<com.android.build.gradle.LibraryExtension>()?.apply {
        defaultConfig {
            compileOptions {
                targetCompatibility = JavaVersion.VERSION_1_8
                sourceCompatibility = JavaVersion.VERSION_1_8
            }
        }
    }
}
