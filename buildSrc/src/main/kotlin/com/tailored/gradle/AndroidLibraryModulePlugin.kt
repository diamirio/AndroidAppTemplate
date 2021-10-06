package com.tailored.gradle

import com.android.build.gradle.BaseExtension
import com.tailored.gradle.helper.Config
import com.tailored.gradle.helper.androidLibrary
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.SourceSet

class AndroidLibraryModulePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.configurePlugins()
        target.configureAndroid()
    }

    private fun Project.configurePlugins() {
        pluginManager.apply("com.android.library")
        pluginManager.apply("kotlin-android")
        pluginManager.apply("kotlin-parcelize")
        pluginManager.apply("kotlin-kapt")
    }

    private fun Project.configureAndroid() {
        androidLibrary().apply {
            compileSdkVersion(Config.compileSdkVersion)
            configureBasics()
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
            targetSdkVersion(Config.targetSdkVersion)
            minSdkVersion(Config.minSdkVersion)
        }
    }

}