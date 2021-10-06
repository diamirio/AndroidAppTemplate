buildscript {
    repositories {
        maven { url = uri("https://maven.tailored-apps.com/repository/proxy-google/") }
        maven { url = uri("https://maven.tailored-apps.com/repository/proxy-maven-plugins/") }
        maven { url = uri("https://maven.tailored-apps.com/repository/proxy-maven-central/") }
    }

    dependencies {
        classpath(Libs.com_android_tools_build_gradle)
        classpath(Libs.kotlin_gradle_plugin)
        classpath(Libs.de_fayard_refreshversions_gradle_plugin)
        classpath(Libs.easylauncher)
        classpath(Libs.ktlint_gradle)
        classpath(Libs.navigation_safe_args_gradle_plugin)
    }
}

apply(plugin = "de.fayard.refreshVersions")
apply(plugin = "org.jlleitschuh.gradle.ktlint")
apply(plugin = "kotlin")

allprojects {
    repositories {
        maven { url = uri("https://maven.tailored-apps.com/repository/proxy-google/") }
        maven { url = uri("https://maven.tailored-apps.com/repository/proxy-maven-central/") }
        jcenter()
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }
}