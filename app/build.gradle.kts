// Module app (entry point) qui assemble les features et démarre Koin
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.projet1diiage"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.projet1diiage"
        minSdk = 29
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
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
    buildFeatures {
        compose = true
    }
}

dependencies {
    // Koin (via BOM)
    implementation(platform("io.insert-koin:koin-bom:3.5.6"))
    implementation("io.insert-koin:koin-android")
    implementation("io.insert-koin:koin-androidx-compose")

    implementation(project(":core:ui"))
    implementation(project(":features:StarWars:domain"))
    implementation(project(":features:StarWars:data"))
    implementation(project(":features:StarWars:ui"))

    // Compose (BOM + bundles)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.bundles.compose.ui)
    debugImplementation(libs.androidx.compose.ui.tooling)

    // Lifecycle / ViewModel Compose (garde un seul set de versions)
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6")

    // Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.8.2")
}
