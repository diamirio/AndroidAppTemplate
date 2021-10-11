plugins {
    id("kotlin-library-convention")
}

dependencies {
    api(Libs.koin_core)

    api(Kotlin.stdlib.jdk8)
}