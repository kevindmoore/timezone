import org.jetbrains.compose.compose

plugins {
//    id("com.android.library")
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
//    android()
//    jvm("desktop")
    jvm {}

    sourceSets {
        named("commonMain") {
            kotlin.srcDirs("src/commonMain/kotlin")
            dependencies {
                implementation(project(":shared"))
                // Koin
//                implementation(Deps.Koin.core)
//                implementation(Deps.Koin.jvm)

                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                api(compose.ui)
                api(compose.materialIconsExtended)
            }
        }
 /*       named("androidMain") {
            kotlin.srcDirs("src/jvmMain/kotlin")
            dependencies {
                api(Deps.AndroidX.appcompat)
                api(Deps.AndroidX.core_ktx)
            }
        }
        named("desktopMain") {
            kotlin.srcDirs("src/jvmMain/kotlin")
            resources.srcDirs("src/resources")
            dependencies {
                api(compose.desktop.common)
            }
        }
*/    }
}

/*
android {
    compileSdk = Versions.compile_sdk

    defaultConfig {
        minSdk = Versions.min_sdk
        targetSdk = Versions.target_sdk
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    sourceSets {
        named("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            res.srcDirs("src/androidMain/res", "src/commonMain/resources")
        }
    }
}
dependencies {
    implementation("androidx.compose.ui:ui-tooling-preview:+")
}
*/
