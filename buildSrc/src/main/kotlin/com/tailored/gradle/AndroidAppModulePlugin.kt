package com.tailored.gradle

import com.android.build.gradle.BaseExtension
import com.tailored.gradle.helper.Config
import com.tailored.gradle.helper.androidApp
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.SourceSet

class AndroidAppModulePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.configurePlugins()
        target.configureAndroid()
    }

    private fun Project.configurePlugins() {
        pluginManager.apply("com.android.application")
        pluginManager.apply("kotlin-android")
        pluginManager.apply("kotlin-parcelize")
        pluginManager.apply("kotlin-kapt")
    }

    private fun Project.configureAndroid() {
        androidApp().apply {
            compileSdkVersion(Config.compileSdkVersion)
            configureBasics()
            configureRuntime()
            configureSourceSets()
            configureTesting()
            configureLinter()
        }
    }

    private fun BaseExtension.configureSourceSets() {
        sourceSets {
            getByName(SourceSet.MAIN_SOURCE_SET_NAME).java.srcDir("src/main/kotlin")
            getByName(SourceSet.TEST_SOURCE_SET_NAME).java.srcDir("src/test/kotlin")
            getByName("androidTest").java.srcDir("src/androidTest/kotlin")
        }
    }

    private fun BaseExtension.configureRuntime() {
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }

    private fun BaseExtension.configureTesting() {
        defaultConfig {
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        testOptions {
            unitTests.isReturnDefaultValues = true
        }
    }

    private fun BaseExtension.configureLinter() {
        lintOptions {
            isWarningsAsErrors = true
            isAbortOnError = true
        }
    }

    private fun BaseExtension.configureBasics() {
        defaultConfig {
            versionCode = Config.versionCode
            versionName = Config.versionName

            applicationId = Config.applicationId


            // TODO: How to in gradle kts dsl
            //setProperty("archivesBaseName", "AndroidAppTemplate-v$versionName-b$versionCode")

            targetSdkVersion(Config.targetSdkVersion)
            minSdkVersion(Config.minSdkVersion)
        }
    }

}