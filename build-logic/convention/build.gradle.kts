import kotlin.Suppress

plugins {
    `kotlin-dsl`
    `groovy-gradle-plugin`
}

group = "com.example.apps.altabib.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly("com.android.tools.build:gradle:8.5.2")
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.21")
}
