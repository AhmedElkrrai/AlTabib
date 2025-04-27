plugins {
    id("altabib.feature.data.library")
}

android {
    namespace = "com.example.altabib.doctors.data"
}


dependencies {
    implementation(project(":featuers:doctors:domain"))
    implementation(project(":featuers:user:domain"))
}
