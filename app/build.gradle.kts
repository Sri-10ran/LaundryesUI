plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.gms.google-services")



}

android {
    namespace = "com.example.laundry"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.laundry"
        minSdk = 25
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
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
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.googleid)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.billing)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.firebase.auth)
    implementation(libs.play.services.auth)


    // Correct Credential Manager dependencies
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth.v150rc01)
    implementation(libs.androidx.credentials.v150rc01)
    implementation(platform(libs.firebase.bom))
    implementation(libs.androidx.credentials.v120alpha04)
    implementation(libs.googleid)

   // Ensure this version or newer is used
    implementation(libs.androidx.lifecycle.viewmodel.compose) // Required for viewModel()
    implementation(libs.ui)
    implementation(libs.material3)
    implementation(libs.play.services.auth.v2070)


    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.converter.gson)

    implementation(files("/libs/OlaMapSdk-1.6.0.aar"))

    implementation (libs.android.sdk)
    implementation (libs.android.plugin.annotation.v9)
    implementation (libs.android.plugin.markerview.v9)
    implementation(libs.checkout)


}