plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("kotlin-parcelize")
}

android {
    compileSdk = Config.compileSdkVersion

    defaultConfig {
        versionCode = Config.versionCode
        versionName = Config.versionName

        applicationId = Config.applicationId

        targetSdk = Config.targetSdkVersion
        minSdk = Config.minSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    sourceSets {
        getByName(SourceSet.MAIN_SOURCE_SET_NAME).java.srcDir("src/main/kotlin")
        getByName(SourceSet.TEST_SOURCE_SET_NAME).java.srcDir("src/test/kotlin")
        getByName("androidTest").java.srcDir("src/androidTest/kotlin")
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }

    lint {
        isWarningsAsErrors = true
        isAbortOnError = true
    }
}

dependencies {
    testImplementation(Libs.junit_junit)
    testImplementation(Libs.koin_test)
    testImplementation(Libs.kotlin_test)
    testImplementation(Libs.kotlinx_coroutines_test)
    testImplementation(Libs.mockk)

    androidTestImplementation(Libs.androidx_test_core)
    androidTestImplementation(Libs.androidx_test_ext_junit)
    androidTestImplementation(Libs.androidx_test_rules)
    androidTestImplementation(Libs.androidx_test_runner)
    androidTestImplementation(Libs.espresso_contrib)
    androidTestImplementation(Libs.espresso_core)
    androidTestImplementation(Libs.espresso_intents)
    androidTestImplementation(Libs.fragment_testing)
    androidTestImplementation(Libs.kotlin_test)
    androidTestImplementation(Libs.kotlinx_coroutines_test)
    androidTestImplementation(Libs.lifecycle_runtime_testing)
}