plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.gms.google-services")
//    id ("com.google.firebase.crashlytics")
}

android {
    namespace = "com.awatech.cuchatapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.awatech.cuchatapp"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

//    implementation ("com.google.firebase:firebase-crashlytics:18.6.1")

    implementation("com.google.android.gms:play-services-auth:20.7.0") // Google Sign-In
    implementation("com.google.android.gms:play-services-base:18.4.0")

    implementation("com.firebaseui:firebase-ui-auth:8.0.2")


    implementation("androidx.compose.ui:ui:1.6.5")
    implementation("androidx.compose.material3:material3:1.2.1")
    implementation("androidx.navigation:navigation-compose:2.7.7")

    implementation("com.github.bumptech.glide:glide:4.16.0")  // For image preview/loading


    implementation(platform("com.google.firebase:firebase-bom:34.0.0"))
    implementation("com.google.firebase:firebase-analytics")

    implementation(platform("com.google.firebase:firebase-bom:32.6.0"))
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-firestore")

    // Firebase BoM - ensures all versions match
    implementation(platform("com.google.firebase:firebase-bom:32.8.0"))

// Required
    implementation("com.google.firebase:firebase-analytics")

// Auth (Email, Password, Phone, Google)
    implementation("com.google.firebase:firebase-auth")

// Realtime Database
    implementation("com.google.firebase:firebase-database")

// Firestore (Cloud Firestore)
    implementation("com.google.firebase:firebase-firestore")

// Firebase Cloud Storage
    implementation("com.google.firebase:firebase-storage")

// Firebase Cloud Messaging (for Push Notifications)
    implementation("com.google.firebase:firebase-messaging")

// Firebase Crashlytics
//    implementation("com.google.firebase:firebase-crashlytics")

// Firebase Remote Config (Optional)
    implementation("com.google.firebase:firebase-config")

// Firebase In-App Messaging (Optional)
    implementation("com.google.firebase:firebase-inappmessaging-display")


    // Material 2 (older)
    implementation ("androidx.compose.material:material:1.6.1")

    implementation ("androidx.navigation:navigation-compose:2.7.7")


    // Material 3 (recommended in 2025)
    implementation ("androidx.compose.material3:material3:1.2.1")


    // Compose core
    implementation ("androidx.compose.ui:ui:1.6.1")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.6.1")
    implementation ("androidx.compose.runtime:runtime-livedata:1.6.1")

// ViewModel support
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

// Navigation
    implementation ("androidx.navigation:navigation-compose:2.7.7")

// Image loading (Coil)
    implementation ("io.coil-kt:coil-compose:2.6.0")

// Pager & Indicators
    implementation ("com.google.accompanist:accompanist-pager:0.32.0")
    implementation ("com.google.accompanist:accompanist-pager-indicators:0.32.0")


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.play.services.maps)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}