plugins {
    id("kotlin-library-convention")
}

dependencies {
    implementation(Libs.koin_core)

    implementation(Kotlin.stdlib.jdk8)
}