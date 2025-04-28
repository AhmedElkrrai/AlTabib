plugins {
    id("altabib.feature.data.library")
}

android {
    namespace = "com.example.altabib.analytics.data"
}


dependencies {
    implementation(project(":featuers:analytics:domain"))
}
