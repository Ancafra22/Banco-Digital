plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.bancodigital"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.bancodigital"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
        viewBinding = true
    }
}

dependencies {
    // AndroidX Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material.v1120)
    implementation(libs.androidx.constraintlayout.v220)
    implementation(libs.kotlinx.coroutines.core)
    //implementation(libs.androidx.navigation.navigation.ui.ktx)

    // Testes
    testImplementation(libs.junit)
    androidTestImplementation("androidx.test.ext:junit:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")

    // Firebase
    implementation(libs.firebase.bom.v3370)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.database.ktx)

    // Navigation
    val navVersion = "2.9.6"
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    val lifecycleVersion = "2.9.4"
    // ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx.v294)
    implementation(libs.androidx.lifecycle.livedata.ktx)

    // Hilt + KSP
    val hiltVersion = "2.52"
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    //mask edittext
    //https://github.com/VicMikhailau/MaskedEditText
    implementation(libs.maskededittext)
}
