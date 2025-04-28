plugins {
    id("altabib.feature.domain.library")
}

dependencies {
    implementation(project(":featuers:user:domain"))
    implementation(project(":featuers:doctors:domain"))
}
