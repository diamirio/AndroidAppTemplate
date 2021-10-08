plugins {
    id("com.tailored.gradle.android-library")
}

apply(from = rootProject.file("gradle/test-dependencies.gradle"))
apply(from = rootProject.file("gradle/test-dependencies-android.gradle"))

android.buildFeatures.viewBinding = true

dependencies {
    api(Libs.loco_core)
    api(Libs.util_permissions)
    api(Libs.util_ui)
    api(Libs.koin_androidx_scope)
    api(Libs.koin_androidx_viewmodel)
    api(Libs.koin_androidx_fragment)
    api(Libs.flowbinding_activity)
    api(Libs.flowbinding_android)
    api(Libs.flowbinding_appcompat)
    api(Libs.flowbinding_core)
    api(Libs.flowbinding_drawerlayout)
    api(Libs.flowbinding_lifecycle)
    api(Libs.flowbinding_material)
    api(Libs.flowbinding_navigation)
    api(Libs.flowbinding_preference)
    api(Libs.flowbinding_recyclerview)
    api(Libs.flowbinding_swiperefreshlayout)
    api(Libs.flowbinding_viewpager2)
    api(Libs.control_core)

    api(Koin.android)
    api(Kotlin.stdlib.jdk8)
    api(Google.android.material)
    api(AndroidX.core.ktx)
    api(AndroidX.activityKtx)
    api(AndroidX.appCompat)
    api(AndroidX.browser)
    api(AndroidX.constraintLayout)
    api(AndroidX.fragmentKtx)
    api(AndroidX.lifecycle.runtimeKtx)
    api(AndroidX.lifecycle.viewModelKtx)
    api(AndroidX.navigation.fragmentKtx)
    api(AndroidX.navigation.uiKtx)
    api(AndroidX.viewPager2)

    debugApi(Square.leakCanary.android)
    releaseApi(Square.leakCanary.objectWatcher.android)
}
