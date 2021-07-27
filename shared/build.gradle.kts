import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
//    id("org.jetbrains.kotlin.multiplatform")
//    kotlin("multiplatform") version "1.5.10"
    kotlin("native.cocoapods")
    id("com.android.library")
}

version = "1.0"

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iosTarget("ios") {}

    cocoapods {
        summary = "Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        frameworkName = "shared"
        podfile = project.file("../timezoneIOS/Podfile")
    }
    
    sourceSets {
        named("commonMain") {
            kotlin.srcDirs("src/commonMain/kotlin")
            dependencies {
                implementation(Deps.JetBrains.datetime)
                // koin
                api(Deps.Koin.core)
//                api(Deps.Koin.test)

                implementation(Deps.napier)
                // Coroutines
                implementation(Deps.Coroutines.common)
            }
        }
/*
        named("commonTest") {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        named("androidMain") {

        }
        named("androidTest") {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        named("iosMain") {

        }
        named("iosTest") {

        }
*/
    }
}

android {
    compileSdk =  Versions.compile_sdk
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = Versions.min_sdk
        targetSdk = Versions.target_sdk
    }
}