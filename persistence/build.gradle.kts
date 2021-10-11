plugins {
    id("android-library-convention")
}

dependencies {
    implementation(Libs.koin_core)

    implementation(AndroidX.room.runtime)
    kapt(AndroidX.room.compiler)
    implementation(AndroidX.room.ktx)

    implementation(Kotlin.stdlib.jdk8)
    implementation(KotlinX.Coroutines.core)
    implementation(JakeWharton.timber)

    implementation(project(":core"))
}