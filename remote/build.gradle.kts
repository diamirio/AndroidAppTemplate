plugins {
    id("com.tailored.gradle.kotlin-library")
}

apply(from = rootProject.file("gradle/test-dependencies.gradle"))

dependencies {
    implementation(Libs.gson)
    implementation(Libs.koin_core)

    implementation(Kotlin.stdlib.jdk8)
    implementation(KotlinX.Coroutines.core)
    implementation(JakeWharton.timber)

    implementation(Square.okHttp3.okHttp)
    implementation(Square.okHttp3.loggingInterceptor)

    implementation(Square.retrofit2.retrofit)
    implementation(Square.retrofit2.converter.gson)

    implementation(project(":core"))
}