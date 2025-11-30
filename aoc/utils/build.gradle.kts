plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    linuxX64()
    mingwX64()
}