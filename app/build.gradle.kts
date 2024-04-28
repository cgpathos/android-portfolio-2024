plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.secrets)
}

android {
    namespace = "today.pathos.android.portfolio"
    compileSdk = 34

    defaultConfig {
        applicationId = "today.pathos.android.portfolio"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "0.1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeKotlinCompiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

secrets {
    defaultPropertiesFileName = "local.defaults.properties"
}

dependencies {
    // hilt
    implementation(libs.dagger.hilt.android)
    ksp(libs.dagger.hilt.compiler)
    androidTestImplementation(libs.dagger.hilt.android.testing)
    kspAndroidTest(libs.dagger.hilt.android.compiler)
    testImplementation(libs.dagger.hilt.android.testing)
    kspTest(libs.dagger.hilt.android.compiler)

    // kotlin
    implementation(libs.bundles.kotlin)
    testImplementation(libs.bundles.kotlin.test)

    implementation(libs.core.ktx)

    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.activity.compose)

    // api
    implementation(libs.bundles.retrofit)
    testImplementation(libs.mockwebserver)

    // database
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
    testImplementation(libs.androidx.room.testing)

    implementation(libs.androidx.datastore)

    // compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.bundles.compose.android.test)
    debugImplementation(libs.bundles.compose.ui.test)

    // test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
