// Module UI de la feature StarWars (écrans Compose)
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.projet1diiage.features.StarWars.ui"
    compileSdk = 36

    defaultConfig {
        minSdk = 29
        targetSdk = 36
    }

    buildTypes {
        release { isMinifyEnabled = false }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions { jvmTarget = "11" }

    buildFeatures { compose = true }

}

dependencies {
    implementation(project(":features:StarWars:domain"))
    implementation(project(":features:StarWars:data"))

    // ✅ Un seul BOM (depuis libs.versions.toml)
    implementation(platform(libs.androidx.compose.bom))

    // UI Compose
    implementation(libs.bundles.compose.ui)
    debugImplementation("androidx.compose.ui:ui-tooling")

    // Lifecycle & VM Compose
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.6")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6")

    // Navigation Compose (si tu en as besoin)
    implementation("androidx.navigation:navigation-compose:2.8.2")

    // Koin + Compose
    implementation("io.insert-koin:koin-android:3.5.6")
    implementation("io.insert-koin:koin-androidx-compose:3.5.6")
}
