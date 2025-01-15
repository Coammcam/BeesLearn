plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "fpl.md07.beeslearn"
    compileSdk = 35 // Hạ xuống nếu môi trường chưa hỗ trợ SDK 35

    defaultConfig {
        applicationId = "fpl.md07.beeslearn"
        minSdk = 26
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // AndroidX & Jetpack Compose
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform("androidx.compose:compose-bom:2024.01.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3:1.2.1")
    implementation(files("libs/zpdk-release-v3.1.aar"))
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity)
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Material Design
    implementation("com.google.android.material:material:1.9.0")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.8.5")

    // Image loading
    implementation("io.coil-kt:coil-compose:2.7.0")
    implementation("io.coil-kt:coil-gif:2.7.0")
    implementation("com.github.bumptech.glide:glide:4.16.0")
    kapt("com.github.bumptech.glide:compiler:4.16.0")

    // Retrofit for API calls
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Hilt for Dependency Injection
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    // LiveData and Coroutines
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.7")
    implementation("androidx.compose.runtime:runtime-livedata:1.7.6")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.1")

    // Media playback (Media3)
    implementation("androidx.media3:media3-exoplayer:1.5.1")
    implementation("androidx.media3:media3-ui:1.5.1")

    // YouTube Player
    implementation("com.pierfrancescosoffritti.androidyoutubeplayer:core:12.1.1")

    // Translator (third-party)
    implementation("com.github.therealbush:translator:1.1.1")

    // Ktor for Networking
    implementation("io.ktor:ktor-client-core:3.0.3")
    implementation("io.ktor:ktor-client-cio:3.0.3")
    implementation("io.ktor:ktor-client-serialization:3.0.3")

    // WorkManager
    implementation("androidx.work:work-runtime-ktx:2.10.0")

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.01.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    implementation ("com.google.accompanist:accompanist-flowlayout:0.30.1")

    //ZaloPay
    implementation ("org.json:json:20210307")
    implementation ("com.squareup.okhttp3:okhttp:4.9.3")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.3")


    implementation ("com.google.code.gson:gson:2.8.8")
    implementation ("com.pierfrancescosoffritti.androidyoutubeplayer:core:11.1.0")

    implementation ("com.github.therealbush:translator:1.1.1")

    implementation ("com.github.therealbush:translator:1.1.1")
    implementation("io.ktor:ktor-client-core:2.0.0")
    implementation("io.ktor:ktor-client-cio:2.0.0")
    implementation("io.ktor:ktor-client-serialization:2.0.0")

    implementation ("androidx.work:work-runtime-ktx:2.8.1")

    implementation ("com.squareup.okhttp3:okhttp:4.9.3")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")

}
