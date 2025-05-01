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
            "\"458416682788-glifk6effmqg69jctupti3hrcmlv7l22.apps.googleusercontent.com\""
        )
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.google.play.services.auth)
}
