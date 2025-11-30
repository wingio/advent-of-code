plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
}

group = "xyz.wingio"
version = "2025"

allprojects {
    repositories {
        mavenCentral()
    }
}