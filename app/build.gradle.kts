plugins {
    id("com.tailored.gradle.android-app")
    id("com.akaita.android.easylauncher")
    id("androidx.navigation.safeargs.kotlin")
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

    flavorDimensions.add("backend")
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

apply(from = rootProject.file("gradle/test-dependencies.gradle"))
apply(from = rootProject.file("gradle/test-dependencies-android.gradle"))

dependencies {
    implementation(project(":core"))
    implementation(project(":base-ui"))

    implementation(Libs.coil)
}

/*
easylauncher {
    iconNames "@mipmap/ic_launcher_foreground"
    foregroundIconNames "@mipmap/ic_launcher_foreground"

    defaultFlavorNaming = true

    buildTypes {
        debug {
            filters = customColorRibbonFilter("debug", "#47BAF2", "#ffffff")
        }
        release {}
    }
}
*/
