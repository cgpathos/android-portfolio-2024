plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.androidxRoom)
    alias(libs.plugins.secrets)
}

android {
    namespace = "today.pathos.android.portfolio.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    }
    room {
        schemaDirectory("$projectDir/schemas")
    }
    packaging {
        resources {
            excludes += "/META-INF/{LICENSE*.md}"
        }
    }
}

secrets {
    defaultPropertiesFileName = "local.defaults.properties"
}

dependencies {
    implementation(project(":common"))
    implementation(project(":entity"))
    implementation(project(":domain"))

    // hilt
    implementation(libs.dagger.hilt.android)
    ksp(libs.dagger.hilt.compiler)
//    androidTestImplementation(libs.dagger.hilt.android.testing)
//    kspAndroidTest(libs.dagger.hilt.android.compiler)
//    testImplementation(libs.dagger.hilt.android.testing)
//    kspTest(libs.dagger.hilt.android.compiler)

    // kotlin
    implementation(libs.bundles.kotlin)
    testImplementation(libs.bundles.kotlin.test)

    // api
    implementation(libs.bundles.retrofit)
    testImplementation(libs.mockwebserver)

    // database
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
    testImplementation(libs.androidx.room.testing)

    implementation(libs.androidx.datastore)

    // test
    testImplementation(libs.junit)
    testImplementation(libs.mockk.android)
    testImplementation(libs.mockk.agent)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.mockk.android)
    androidTestImplementation(libs.mockk.agent)
}
