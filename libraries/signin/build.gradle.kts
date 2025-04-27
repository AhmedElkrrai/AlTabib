plugins {
    id("altabib.android.library")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.altabib.signin"

    defaultConfig {
        buildConfigField(
            "String",
            "DEFAULT_WEB_CLIENT_ID",
            "\"458416682788-tmftf3752n7pom7pgee6jg4n6io81e1e.apps.googleusercontent.com\""
        )
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.google.play.services.auth)
}
