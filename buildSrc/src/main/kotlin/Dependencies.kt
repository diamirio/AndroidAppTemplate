@file:Suppress("unused", "SpellCheckingInspection")

object Versions {
    const val Kotlin = "1.3.40"
    const val Reaktor = "1.0.17"
    const val AndroidAppUtil = "14"
    const val AndroidX = "1.1.0-beta01"
    const val Navigation = "2.0.0"
    const val Koin = "2.0.1"
    const val RxBinding = "3.0.0-alpha2"
    const val Lifecycle = "2.0.0"
    const val Retrofit = "2.6.0"
    const val OkHttp = "4.0.0"
    const val Leakcanary = "1.6.3"
    const val Glide = "4.9.0"
    const val Mockito = "2.28.2"
    const val Powermock = "2.0.2"
    const val Espresso = "3.1.0"
    const val AndroidXTest = "1.1.0"
}

object BuildScriptDependencies {
    const val AndroidBuildTools = "com.android.tools.build:gradle:3.4.1"
    const val KotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Kotlin}"
    const val NavigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.Navigation}"

    const val DependencyVersions = "com.github.ben-manes:gradle-versions-plugin:0.21.0"
    const val EasyLauncher = "com.akaita.android:easylauncher:1.3.1"
    const val GraphViz = "guru.nidi:graphviz-java:0.5.3"
    const val DependencyGraph = "com.vanniktech:gradle-dependency-graph-generator-plugin:0.5.0"
}

object ModuleDependencies {
    const val KotlinStd = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.Kotlin}"

    const val Reaktor = "at.florianschuster.reaktor:reaktor-android-koin:${Versions.Reaktor}"

    val AndroidAppUtil = mapOf(
        "UI" to "com.tailoredapps.androidutil:util-ui:${Versions.AndroidAppUtil}",
        "Async" to "com.tailoredapps.androidutil:util-async:${Versions.AndroidAppUtil}",
        "Network" to "com.tailoredapps.androidutil:util-network:${Versions.AndroidAppUtil}",
        "Optional" to "com.tailoredapps.androidutil:util-optional:${Versions.AndroidAppUtil}",
        "Permissions" to "com.tailoredapps.androidutil:util-permissions:${Versions.AndroidAppUtil}",
        "ViewState" to "com.tailoredapps.androidutil:util-viewstate:${Versions.AndroidAppUtil}"
    )

    val UI = mapOf(
        "AppCompat" to "androidx.appcompat:appcompat:${Versions.AndroidX}",
        "Fragment" to "androidx.fragment:fragment:${Versions.AndroidX}",
        "Constraintlayout" to "androidx.constraintlayout:constraintlayout:1.1.3",
        "Material" to "com.google.android.material:material:1.0.0",
        "CoreKTX" to "androidx.core:core-ktx:1.0.1",
        "NavigationCore" to "androidx.navigation:navigation-fragment-ktx:${Versions.Navigation}",
        "NavigationUI" to "androidx.navigation:navigation-ui-ktx:${Versions.Navigation}"
    )

    val Lifecycle = mapOf(
        "Extensions" to "androidx.lifecycle:lifecycle-extensions:${Versions.Lifecycle}",
        "Compiler" to "androidx.lifecycle:lifecycle-compiler:${Versions.Lifecycle}"
    )

    val Koin = mapOf(
        "Core" to "org.koin:koin-core:${Versions.Koin}",
        "Android" to "org.koin:koin-android:${Versions.Koin}",
        "Scope" to "org.koin:koin-androidx-scope:${Versions.Koin}",
        "ViewModel" to "org.koin:koin-androidx-viewmodel:${Versions.Koin}"
    )

    val Rx = mapOf(
        "Java" to "io.reactivex.rxjava2:rxjava:2.2.10",
        "Kotlin" to "io.reactivex.rxjava2:rxkotlin:2.3.0",
        "Relay" to "com.jakewharton.rxrelay2:rxrelay:2.1.0",
        "Android" to "io.reactivex.rxjava2:rxandroid:2.1.1"
    )

    val Networking = mapOf(
        "Retrofit" to "com.squareup.retrofit2:retrofit:${Versions.Retrofit}",
        "RetrofitGsonConverter" to "com.squareup.retrofit2:converter-gson:${Versions.Retrofit}",
        "RetrofitRxJavaAdapter" to "com.squareup.retrofit2:adapter-rxjava2:${Versions.Retrofit}",
        "OkHttp" to "com.squareup.okhttp3:okhttp:${Versions.OkHttp}",
        "OkHttpLogging" to "com.squareup.okhttp3:logging-interceptor:${Versions.OkHttp}"
    )

    val RxBinding = mapOf(
        "RxBinding" to "com.jakewharton.rxbinding3:rxbinding:${Versions.RxBinding}",
        "Core" to "com.jakewharton.rxbinding3:rxbinding-core:${Versions.RxBinding}",
        "Appcompat" to "com.jakewharton.rxbinding3:rxbinding-appcompat:${Versions.RxBinding}",
        "Drawerlayout" to "com.jakewharton.rxbinding3:rxbinding-drawerlayout:${Versions.RxBinding}",
        "Recyclerview" to "com.jakewharton.rxbinding3:rxbinding-recyclerview:${Versions.RxBinding}",
        "Swiperefreshlayout" to "com.jakewharton.rxbinding3:rxbinding-swiperefreshlayout:${Versions.RxBinding}",
        "Viewpager" to "com.jakewharton.rxbinding3:rxbinding-viewpager:${Versions.RxBinding}",
        "Material" to "com.jakewharton.rxbinding3:rxbinding-material:${Versions.RxBinding}"
    )

    const val Gson = "com.google.code.gson:gson:2.8.5"
    const val Timber = "com.jakewharton.timber:timber:4.7.1"
    const val Threeten = "com.jakewharton.threetenabp:threetenabp:1.2.1"

    const val LeakCanaryOp = "com.squareup.leakcanary:leakcanary-android:${Versions.Leakcanary}"
    const val LeakCanaryNoOp = "com.squareup.leakcanary:leakcanary-android-no-op:${Versions.Leakcanary}"

    val Glide = mapOf(
        "Core" to "com.github.bumptech.glide:glide:${Versions.Glide}",
        "Compiler" to "com.github.bumptech.glide:compiler:${Versions.Glide}"
    )

    val Test = mapOf(
        "Junit" to "junit:junit:4.12",
        "Mockito" to "org.mockito:mockito-core:${Versions.Mockito}",
        "MockitoKotlin" to "com.nhaarman:mockito-kotlin-kt1.1:1.5.0",
        "Powermock" to "org.powermock:powermock-module-junit4:${Versions.Powermock}",
        "PowermockRule" to "org.powermock:powermock-module-junit4-rule:${Versions.Powermock}",
        "PowermockMockito2" to "org.powermock:powermock-api-mockito2:${Versions.Powermock}",
        "KotlinReflect" to "org.jetbrains.kotlin:kotlin-reflect:${Versions.Kotlin}",
        "Jsr305" to "com.google.code.findbugs:jsr305:3.0.2",
        "Mockk" to "io.mockk:mockk:1.9.3",
        "Kluent" to "org.amshove.kluent:kluent:1.51",
        "KoinTest" to "org.koin:koin-test:${Versions.Koin}"
    )

    val AndroidTest = mapOf(
        "Core" to "androidx.test:core:1.0.0",
        "Runner" to "androidx.test:runner:${Versions.AndroidXTest}",
        "Rules" to "androidx.test:rules:${Versions.AndroidXTest}",
        "AndroidXTruth" to "androidx.test.ext:truth:${Versions.AndroidXTest}",
        "AndroidJunit" to "androidx.test.ext:junit:${Versions.AndroidXTest}",
        "Truth" to "com.google.truth:truth:1.0-rc2",
        "Espresso" to "androidx.test.espresso:espresso-core:${Versions.Espresso}",
        "EspressoContrib" to "androidx.test.espresso:espresso-contrib:${Versions.Espresso}",
        "EspressoIntents" to "androidx.test.espresso:espresso-intents:${Versions.Espresso}",
        "EspressoWeb" to "androidx.test.espresso:espresso-web:${Versions.Espresso}",
        "Mockito" to "org.mockito:mockito-android:${Versions.Mockito}"
    )
}