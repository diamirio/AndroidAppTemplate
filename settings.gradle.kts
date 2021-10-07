pluginManagement {
    repositories {
        maven { url = uri("https://maven.tailored-apps.com/repository/proxy-google/") }
        maven { url = uri("https://maven.tailored-apps.com/repository/proxy-maven-plugins/") }
        maven { url = uri("https://maven.tailored-apps.com/repository/proxy-maven-central/") }
    }

    // For legacy plugins that don't support the new plugin syntax yet
    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "com.android.application" -> useModule("com.android.tools.build:gradle:${requested.version}")
                "com.akaita.android.easylauncher" -> useModule("com.akaita.android:easylauncher:${requested.version}")
                "androidx.navigation.safeargs.kotlin" -> useModule("androidx.navigation:navigation-safe-args-gradle-plugin:${requested.version}")
            }
        }
    }
}

plugins {
    id("de.fayard.refreshVersions") version "0.23.0"
}

refreshVersions {
    enableBuildSrcLibs()
}

includeBuild("buildLogic")
include("app", "core", "base-ui")
