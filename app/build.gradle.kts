plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.oneinone_alltoolsapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.dhyey.oneinone_alltoolsapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 2
        versionName = "2"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true // Enable code shrinking and obfuscation
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            var shrinkResources = true // Remove unused resources;
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.firestore.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // AndroidX Libraries
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("androidx.fragment:fragment:1.3.6")
    implementation("androidx.viewpager2:viewpager2:1.0.0")

    // Material Design
    implementation("com.google.android.material:material:1.4.0")

    // Networking
    implementation("com.squareup.okhttp3:okhttp:4.9.0")

    // Date and Time
    implementation("joda-time:joda-time:2.10.14")

    // Math Expression Parser
    implementation("net.objecthunter:exp4j:0.4.8")

    // GIF Support
    implementation("pl.droidsonroids.gif:android-gif-drawable:1.2.23")

    // ZXing Libraries
    implementation("com.google.zxing:core:3.5.2")
    implementation("com.journeyapps:zxing-android-embedded:4.1.0")

    // CameraX Libraries
    implementation("androidx.camera:camera-core:1.0.1")
    implementation("androidx.camera:camera-camera2:1.0.1")
    implementation("androidx.camera:camera-lifecycle:1.0.1")

    implementation("androidx.room:room-runtime:2.5.2")
    annotationProcessor("androidx.room:room-compiler:2.5.2")
}
