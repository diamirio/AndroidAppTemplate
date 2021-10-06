plugins {
    id("com.tailored.gradle.android-library")
}

apply(from = rootProject.file("gradle/test-dependencies.gradle"))
apply(from = rootProject.file("gradle/test-dependencies-android.gradle"))

android.buildFeatures.viewBinding = true

dependencies {
    api(Libs.activity_ktx)
    api(Libs.appcompat)
    api(Libs.browser)
    api(Libs.constraintlayout)
    api(Libs.control_core)
    api(Libs.core_ktx)
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
    api(Libs.fragment_ktx)
    api(Libs.koin_android)
    api(Libs.koin_androidx_scope)
    api(Libs.koin_androidx_viewmodel)
    api(Libs.koin_androidx_fragment)
    api(Libs.kotlin_stdlib_jdk8)
    api(Libs.lifecycle_runtime_ktx)
    api(Libs.lifecycle_viewmodel_ktx)
    api(Libs.loco_core)
    api(Libs.material)
    api(Libs.navigation_fragment_ktx)
    api(Libs.navigation_ui_ktx)
    api(Libs.util_permissions)
    api(Libs.util_ui)
    api(Libs.viewpager2)

    debugApi(Libs.leakcanary_android)
    releaseApi(Libs.leakcanary_object_watcher_android)
}
