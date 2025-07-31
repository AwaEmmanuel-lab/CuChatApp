// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("com.google.gms.google-services") version "4.4.3" apply false
}
//
//buildscript {
//    repositories {
//        google()
//        mavenCentral()
//    }
//    dependencies {
//        classpath ("com.android.tools.build:gradle:8.0.2") // or your version
//        classpath ("com.google.gms:google-services:4.4.3") // ✅ already needed for Firebase
//        classpath ("com.google.firebase:firebase-crashlytics-gradle:2.9.9") // ✅ ADD THIS LINE
//    }
//}
