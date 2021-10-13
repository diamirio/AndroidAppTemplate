plugins {
    id("android-app-convention")
    id("androidx.navigation.safeargs.kotlin")
    id("com.starter.easylauncher")
}

android {
    signingConfigs {
        create("release") {
            keyAlias = "mykeyalias"
            keyPassword = "mykeypassword"
            storeFile = file("../keystore.jks")
            storePassword = "mykeystorepassword"
        }
    }

    buildTypes {
        getByName("debug") {
            versionNameSuffix = "-DEBUG"
            isMinifyEnabled = false
        }
        getByName("release") {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    flavorDimensions("backend")
    productFlavors {
        create("dev") {
            applicationIdSuffix = ".dev"
            resValue("string", "app_name", "AndroidAppTemplate Dev")
            resValue("string", "leak_canary_display_activity_label", "AndroidAppTemplate Leaks")
            buildConfigField("String", "BASE_URL", "\"https://restcountries.eu/rest/v2/\"")
            dimension = "backend"
        }
        create("prod") {
            resValue("string", "app_name", "AndroidAppTemplate")
            buildConfigField("String", "BASE_URL", "\"https://restcountries.eu/rest/v2/\"")
            dimension = "backend"
        }
    }

    bundle {
        language {
            enableSplit = false
        }
        density {
            enableSplit = false
        }
        abi {
            enableSplit = true
        }
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":remote"))
    implementation(project(":persistence"))
    implementation(project(":repository"))
    implementation(project(":base-ui"))

    implementation(JakeWharton.timber)
    implementation(AndroidX.activityKtx)
    implementation(AndroidX.constraintLayout)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.navigation.fragmentKtx)
    implementation(AndroidX.navigation.uiKtx)

    implementation(Libs.coil)
    implementation(Libs.loco_core)
    implementation(Libs.koin_android)
    implementation(Libs.koin_androidx_scope)
    implementation(Libs.koin_androidx_viewmodel)
    implementation(Libs.koin_androidx_fragment)
}


easylauncher {
    defaultFlavorNaming(true)

    //val foregroundIconNamesMethod = javaClass.getMethod("foregroundIconNames", Array<String>::class.java).apply { isAccessible = true }
    //foregroundIconNamesMethod.invoke(this, arrayOf("@mipmap/ic_launcher_foreground"))
    //iconNames = arrayOf("@mipmap/ic_launcher_foreground")
    //foregroundIconNames = arrayOf("@mipmap/ic_launcher_foreground")

    buildTypes {
        create("debug") {
            setFilters(customRibbon(label = "debug", ribbonColor = "#47BAF2", labelColor = "#ffffff"))
        }
        create("release") {}
    }
}