plugins {
    id("android-library-convention")
}

dependencies {
    implementation(Libs.koin_core)

    implementation(Kotlin.stdlib.jdk8)
    implementation(KotlinX.Coroutines.core)
    implementation(JakeWharton.timber)

    implementation(projects.core)
    implementation(projects.remote)
    implementation(projects.persistence)
}