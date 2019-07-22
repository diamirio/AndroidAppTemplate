@file:Suppress("unused", "SpellCheckingInspection")

typealias DependencyItem = CharSequence

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
    const val AndroidXTest = "1.2.0"
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

    const val Gson = "com.google.code.gson:gson:2.8.5"
    const val Timber = "com.jakewharton.timber:timber:4.7.1"
    const val Threeten = "com.jakewharton.threetenabp:threetenabp:1.2.1"

    const val LeakCanaryOp = "com.squareup.leakcanary:leakcanary-android:${Versions.Leakcanary}"
    const val LeakCanaryNoOp = "com.squareup.leakcanary:leakcanary-android-no-op:${Versions.Leakcanary}"
}

// todo split up and put into ModuleDependencies
enum class AndroidAppUtil(val dep: String): DependencyItem by dep {
    UI("com.tailoredapps.androidutil:util-ui:${Versions.AndroidAppUtil}"),
    Async("com.tailoredapps.androidutil:util-async:${Versions.AndroidAppUtil}"),
    Network("com.tailoredapps.androidutil:util-network:${Versions.AndroidAppUtil}"),
    Optional("com.tailoredapps.androidutil:util-optional:${Versions.AndroidAppUtil}"),
    Permissions("com.tailoredapps.androidutil:util-permissions:${Versions.AndroidAppUtil}"),
    ViewState("com.tailoredapps.androidutil:util-viewstate:${Versions.AndroidAppUtil}");
    
    override fun toString() = dep
}

// todo split up and put into ModuleDependencies
enum class UI(val dep: String) : DependencyItem by dep {
    AppCompat("androidx.appcompat:appcompat:${Versions.AndroidX}"),
    Fragment("androidx.fragment:fragment:${Versions.AndroidX}"),
    Constraintlayout("androidx.constraintlayout:constraintlayout:1.1.3"),
    Material("com.google.android.material:material:1.0.0"),
    CoreKTX("androidx.core:core-ktx:1.0.1"),
    NavigationCore("androidx.navigation:navigation-fragment-ktx:${Versions.Navigation}"),
    NavigationUI("androidx.navigation:navigation-ui-ktx:${Versions.Navigation}");

    override fun toString() = dep
}

// todo split up and put into ModuleDependencies
enum class Lifecycle(val dep: String) : DependencyItem by dep {
    Extensions("androidx.lifecycle:lifecycle-extensions:${Versions.Lifecycle}"),
    Compiler("androidx.lifecycle:lifecycle-compiler:${Versions.Lifecycle}");

    override fun toString() = dep
}

// todo split up and put into ModuleDependencies
enum class Koin(val dep: String) : DependencyItem by dep {
    Core("org.koin:koin-core:${Versions.Koin}"),
    Android("org.koin:koin-android:${Versions.Koin}"),
    Scope("org.koin:koin-androidx-scope:${Versions.Koin}"),
    ViewModel("org.koin:koin-androidx-viewmodel:${Versions.Koin}");

    override fun toString() = dep
}

// todo split up and put into ModuleDependencies
enum class Rx(val dep: String) : DependencyItem by dep {
    Java("io.reactivex.rxjava2:rxjava:2.2.10"),
    Kotlin("io.reactivex.rxjava2:rxkotlin:2.3.0"),
    Relay("com.jakewharton.rxrelay2:rxrelay:2.1.0"),
    Android("io.reactivex.rxjava2:rxandroid:2.1.1");

    override fun toString() = dep
}

// todo split up and put into ModuleDependencies
enum class Networking(val dep: String) : DependencyItem by dep {
    Retrofit("com.squareup.retrofit2:retrofit:${Versions.Retrofit}"),
    RetrofitGsonConverter("com.squareup.retrofit2:converter-gson:${Versions.Retrofit}"),
    RetrofitRxJavaAdapter("com.squareup.retrofit2:adapter-rxjava2:${Versions.Retrofit}"),
    OkHttp("com.squareup.okhttp3:okhttp:${Versions.OkHttp}"),
    OkHttpLogging("com.squareup.okhttp3:logging-interceptor:${Versions.OkHttp}");

    override fun toString() = dep
}

// todo split up and put into ModuleDependencies
enum class RxBinding(val dep: String) : DependencyItem by dep {
    Binding("com.jakewharton.rxbinding3:rxbinding:${Versions.RxBinding}"),
    Core("com.jakewharton.rxbinding3:rxbinding-core:${Versions.RxBinding}"),
    Appcompat("com.jakewharton.rxbinding3:rxbinding-appcompat:${Versions.RxBinding}"),
    Drawerlayout("com.jakewharton.rxbinding3:rxbinding-drawerlayout:${Versions.RxBinding}"),
    Recyclerview("com.jakewharton.rxbinding3:rxbinding-recyclerview:${Versions.RxBinding}"),
    Swiperefreshlayout("com.jakewharton.rxbinding3:rxbinding-swiperefreshlayout:${Versions.RxBinding}"),
    Viewpager("com.jakewharton.rxbinding3:rxbinding-viewpager:${Versions.RxBinding}"),
    Material("com.jakewharton.rxbinding3:rxbinding-material:${Versions.RxBinding}");

    override fun toString() = dep
}

// todo split up and put into ModuleDependencies
enum class Glide(val dep: String) : DependencyItem by dep {
    Core("com.github.bumptech.glide:glide:${Versions.Glide}"),
    Compiler("com.github.bumptech.glide:compiler:${Versions.Glide}");

    override fun toString() = dep
}

enum class Tests(private val dependency: String): DependencyItem by dependency {
    Junit("junit:junit:4.12"),
    Mockito("org.mockito:mockito-core:${Versions.Mockito}"),
    MockitoKotlin("com.nhaarman:mockito-kotlin-kt1.1:1.5.0"),
    Powermock("org.powermock:powermock-module-junit4:${Versions.Powermock}"),
    PowermockRule("org.powermock:powermock-module-junit4-rule:${Versions.Powermock}"),
    PowermockMockito2("org.powermock:powermock-api-mockito2:${Versions.Powermock}"),
    KotlinReflect("org.jetbrains.kotlin:kotlin-reflect:${Versions.Kotlin}"),
    Jsr305("com.google.code.findbugs:jsr305:3.0.2"),
    Mockk("io.mockk:mockk:1.9.3"),
    Kluent("org.amshove.kluent:kluent:1.51"),
    KoinTest("org.koin:koin-test:${Versions.Koin}");

    override fun toString() = dependency
}

enum class AndroidTests(private val dependency: String): DependencyItem by dependency {
    Core("androidx.test:core:${Versions.AndroidXTest}"), //todo check which AndroidXTest version have same version codes
    Runner("androidx.test:runner:${Versions.AndroidXTest}"),
    Rules("androidx.test:rules:${Versions.AndroidXTest}"),
    AndroidXTruth("androidx.test.ext:truth:${Versions.AndroidXTest}"),
    AndroidXJunit("androidx.test.ext:junit:1.1.0"),
    Truth("com.google.truth:truth:1.0-rc2"),
    Espresso("androidx.test.espresso:espresso-core:${Versions.Espresso}"),
    EspressoContrib("androidx.test.espresso:espresso-contrib:${Versions.Espresso}"),
    EspressoIntents("androidx.test.espresso:espresso-intents:${Versions.Espresso}"),
    EspressoWeb("androidx.test.espresso:espresso-web:${Versions.Espresso}"),
    Mockito("org.mockito:mockito-android:${Versions.Mockito}");

    override fun toString() = dependency
}