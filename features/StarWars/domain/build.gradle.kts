// Module Domain de la feature StarWars (modèles + use cases)
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.projet1diiage.features.StarWars.domain"
    compileSdk = 36

    defaultConfig {
        minSdk = 29
        targetSdk = 36
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
}
