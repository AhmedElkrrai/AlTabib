plugins {
    id("altabib.feature.presentation.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.user.presentation"
}

dependencies {
    implementation(project(":featuers:user:domain"))
}
