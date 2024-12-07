plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "fpl.md07.beeslearn"
    compileSdk = 34

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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation("androidx.navigation:navigation-compose:2.8.3")

    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation("io.coil-kt:coil-gif:2.5.0")
    implementation("com.github.bumptech.glide:glide:4.12.0")
    kapt("com.github.bumptech.glide:compiler:4.12.0")
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")

    implementation ("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")
    implementation ("androidx.compose.runtime:runtime-livedata:1.5.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("com.google.accompanist:accompanist-flowlayout:0.36.0")

    implementation("com.google.accompanist:accompanist-flowlayout:0.36.0")

    dependencies {
        implementation ("com.google.android.exoplayer:exoplayer:2.19.1")  // Thêm ExoPlayer
        implementation ("androidx.media3:media3-exoplayer:1.0.0")         // Thêm Media3
    }

    implementation ("androidx.media3:media3-exoplayer:1.0.0")
    implementation ("androidx.media3:media3-ui:1.0.0")

    implementation ("com.pierfrancescosoffritti.androidyoutubeplayer:core:11.1.0")

    implementation ("com.github.therealbush:translator:1.1.1")

    implementation ("com.github.therealbush:translator:1.1.1")
    implementation("io.ktor:ktor-client-core:2.0.0")
    implementation("io.ktor:ktor-client-cio:2.0.0")
    implementation("io.ktor:ktor-client-serialization:2.0.0")



}