package com.tailored.gradle.helper


import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.AppExtension
import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType

fun Project.androidLibrary(): LibraryExtension {
    val android = extensions.findByType<LibraryExtension>()
    return android ?: throw GradleException("Project $name is not an Android library project.")
}

fun Project.androidApp(): AppExtension {
    val android = extensions.findByType<AppExtension>()
    return android ?: throw GradleException("Project $name is not an Android app project.")
}