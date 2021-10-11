plugins {
    `java-library`
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.kapt")
}

extensions.getByType<SourceSetContainer>().apply {
    getByName(SourceSet.MAIN_SOURCE_SET_NAME).java.srcDir("src/main/kotlin")
    getByName(SourceSet.TEST_SOURCE_SET_NAME).java.srcDir("src/test/kotlin")
}

dependencies {
    testImplementation(Libs.junit_junit)
    testImplementation(Libs.koin_test)
    testImplementation(Libs.kotlin_test)
    testImplementation(Libs.kotlinx_coroutines_test)
    testImplementation(Libs.mockk)
}