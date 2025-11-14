pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    plugins {
        id("com.android.application") version "8.13.1"
        id("org.jetbrains.kotlin.android") version "2.0.20"
        id("com.google.devtools.ksp") version "2.0.20-1.0.25"
        id("androidx.navigation.safeargs.kotlin") version "2.8.4"
        id("com.google.dagger.hilt.android") version "2.52"
        id("com.google.gms.google-services") version "4.4.2"
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "BancoDigital"
include(":app")
