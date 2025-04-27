plugins {
    id("altabib.feature.presentation.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.doctors.presentation"
}

dependencies {
    implementation(project(":featuers:user:domain"))
    implementation(project(":featuers:doctors:domain"))
    implementation(project(":featuers:settings:domain"))
    implementation(project(":featuers:favorites:domain"))
    implementation(project(":featuers:appointments:domain"))
}
