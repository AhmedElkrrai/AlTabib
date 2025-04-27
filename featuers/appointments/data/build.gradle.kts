plugins {
    id("altabib.feature.data.library")
}

android {
    namespace = "com.example.altabib.appointments.data"
}


dependencies {
    implementation(project(":featuers:appointments:domain"))
}
