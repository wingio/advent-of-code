plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    listOf(
        linuxX64(),
        mingwX64()
    ).forEach {
        it.binaries {
            executable("aoc") {
                entryPoint = "solver.main"
            }
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.clikt)
            implementation(libs.kotlinx.io)
            implementation(libs.kotlinx.coroutines)

            implementation(project(":aoc:utils"))
        }
    }
}