plugins {
    id("com.tailored.gradle.kotlin-library")
}

apply(from = rootProject.file("gradle/test-dependencies.gradle"))

dependencies {
    api(Libs.control_core)
    api(Libs.gson)
    api(Libs.koin_core)
    api(Libs.kotlin_stdlib_jdk8)
    api(Libs.kotlinx_coroutines_core)
    api(Libs.threetenabp)
    api(Libs.timber)

    implementation(Libs.converter_gson)
    implementation(Libs.logging_interceptor)
    implementation(Libs.okhttp)
    implementation(Libs.retrofit)
}