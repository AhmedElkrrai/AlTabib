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
    compileOnly(libs.gradle)
    compileOnly(libs.kotlin.gradle.plugin)
}
