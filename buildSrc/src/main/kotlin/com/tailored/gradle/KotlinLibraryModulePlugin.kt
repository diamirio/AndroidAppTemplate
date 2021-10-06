package com.tailored.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaLibraryPlugin
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.plugin.KaptExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformJvmPlugin

class KotlinLibraryModulePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.configurePlugins()
        target.configureSourceSets()
    }

    private fun Project.configurePlugins() {
        pluginManager.apply(JavaLibraryPlugin::class.java)
        pluginManager.apply("kotlin")
        pluginManager.apply("kotlin-kapt")

    }

    private fun Project.configureSourceSets() {
        project.extensions.getByType<SourceSetContainer>().apply {
            getByName(SourceSet.MAIN_SOURCE_SET_NAME).java.srcDir("src/main/kotlin")
            getByName(SourceSet.TEST_SOURCE_SET_NAME).java.srcDir("src/test/kotlin")
        }
    }

}