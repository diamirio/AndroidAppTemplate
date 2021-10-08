plugins {
    id("com.tailored.gradle.kotlin-library")
}

apply(from = rootProject.file("gradle/test-dependencies.gradle"))

dependencies {
    api(Libs.control_core)
    api(Libs.gson)

    api(Koin.core)
    api(Kotlin.stdlib.jdk8)
    api(KotlinX.Coroutines.core)
    api(JakeWharton.timber)

    implementation(Square.okHttp3.okHttp)
    implementation(Square.okHttp3.loggingInterceptor)

    implementation(Square.retrofit2.retrofit)
    implementation(Square.retrofit2.converter.gson)
}