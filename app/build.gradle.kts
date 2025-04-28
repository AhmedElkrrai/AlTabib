plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.google.services)
}

android {
    namespace = "com.example.altabib"
    compileSdk = rootProject.extra["compileSdk"] as Int

    defaultConfig {
        applicationId = "com.example.altabib"
        minSdk = rootProject.extra["minSdk"] as Int
        targetSdk = rootProject.extra["targetSdk"] as Int
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

    ksp {
        arg("room.schemaLocation", "$projectDir/schemas")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(project(":libraries:core"))
    implementation(project(":libraries:design-system"))
    implementation(project(":libraries:signin"))

    implementation(project(":featuers:profile:domain"))
    implementation(project(":featuers:profile:presentation"))

    implementation(project(":featuers:analytics:data"))
    implementation(project(":featuers:analytics:domain"))
    implementation(project(":featuers:analytics:presentation"))

    implementation(project(":featuers:appointments:data"))
    implementation(project(":featuers:appointments:domain"))
    implementation(project(":featuers:appointments:presentation"))

    implementation(project(":featuers:doctors:data"))
    implementation(project(":featuers:doctors:domain"))
    implementation(project(":featuers:doctors:presentation"))

    implementation(project(":featuers:favorites:domain"))
    implementation(project(":featuers:favorites:presentation"))

    implementation(project(":featuers:settings:data"))
    implementation(project(":featuers:settings:domain"))
    implementation(project(":featuers:settings:presentation"))

    implementation(project(":featuers:user:data"))
    implementation(project(":featuers:user:domain"))
    implementation(project(":featuers:user:presentation"))

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.bundles.koin)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.lottie.compose)

    implementation(libs.firebase.auth)
    implementation(libs.google.play.services.auth)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.gson)

    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    testImplementation(libs.junit)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    debugImplementation(libs.bundles.compose.debug)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
}