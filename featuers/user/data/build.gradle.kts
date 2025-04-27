plugins {
    id("altabib.feature.data.library")
}

android {
    namespace = "com.example.altabib.user.data"
}


dependencies {
    implementation(project(":featuers:user:domain"))
}
