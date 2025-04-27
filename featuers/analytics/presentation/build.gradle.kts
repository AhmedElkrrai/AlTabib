plugins {
    id("altabib.feature.presentation.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.analytics.presentation"
}

dependencies {
    implementation(project(":featuers:user:domain"))
    implementation(project(":featuers:analytics:domain"))
}
