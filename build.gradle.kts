// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath(libs.google.services)
    }
}

plugins {
    id("com.android.application") apply false
    id("org.jetbrains.kotlin.android") apply false
    id("com.google.devtools.ksp") apply false
    id("androidx.navigation.safeargs.kotlin") apply false
    id("com.google.dagger.hilt.android") apply false
    id("com.google.gms.google-services") apply false
}
