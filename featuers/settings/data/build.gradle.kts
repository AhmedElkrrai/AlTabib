plugins {
    id("altabib.feature.data.library")
}

android {
    namespace = "com.example.altabib.settings.data"
}


dependencies {
    implementation(project(":featuers:settings:domain"))
    implementation(project(":featuers:user:domain"))
}
