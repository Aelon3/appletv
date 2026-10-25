plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}
android {
    namespace = "com.aelon.appletv"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.aelon.appletv"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "27i"
    }
    buildTypes { release { isMinifyEnabled = false } }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }
}
