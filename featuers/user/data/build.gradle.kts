plugins {
    id("altabib.feature.data.library")
}

android {
    namespace = "com.example.altabib.user.data"

    defaultConfig {
        buildConfigField("String", "DEFAULT_WEB_CLIENT_ID", "\"458416682788-tmftf3752n7pom7pgee6jg4n6io81e1e.apps.googleusercontent.com\"")
    }

    buildFeatures {
        buildConfig = true
    }
}


dependencies {
    implementation(project(":featuers:user:domain"))
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.google.play.services.auth)
}
