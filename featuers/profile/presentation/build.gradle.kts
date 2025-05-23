plugins {
    id("altabib.feature.presentation.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.profile.presentation"
}

dependencies {
    implementation(project(":featuers:user:domain"))
    implementation(project(":featuers:doctors:domain"))
    implementation(libs.coil.compose)
}
