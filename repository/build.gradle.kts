plugins {
    id("com.tailored.gradle.android-library")
}

apply(from = rootProject.file("gradle/test-dependencies.gradle"))

dependencies {
    implementation(Libs.koin_core)

    implementation(Kotlin.stdlib.jdk8)
    implementation(KotlinX.Coroutines.core)
    implementation(JakeWharton.timber)

    implementation(project(":core"))
    implementation(project(":remote"))
    implementation(project(":persistence"))
}