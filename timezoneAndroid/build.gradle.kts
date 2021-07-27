import Deps.material
import Deps.multiplatformSettings

plugins {
  id("com.android.application")
  kotlin("android")
}

dependencies {
  implementation(project(":shared"))

  with(Deps) {
    implementation(napier)
    implementation(material)
  }
//  with(Deps.Ktor) {
//    implementation(androidCore)
//  }

  implementation(Deps.Kotlin.stdlib)
  // SqlDelight
//  with(Deps.SqlDelight) {
//    implementation(runtimeJdk)
//    implementation(driverAndroid)
//  }

  // Coroutines
  with(Deps.Coroutines) {
    implementation(common)
    implementation(android)
  }

  // Settings
//  implementation(multiplatformSettings)

  // Koin
  with(Deps.Koin) {
    implementation(core)
    implementation(android)
    implementation(compose)
  }
  // AndroidX
//  with(Deps.AndroidX) {
//    implementation(constraintlayout)
//    implementation(appcompat)
//    implementation(core_ktx)
//  }

  //Compose
  with(Deps.Compose) {
    implementation(runtime)
    implementation(runtime_livedata)
    implementation(ui)
    implementation(tooling)
    implementation(foundation)
    implementation(foundationLayout)
    implementation(material)
    implementation(material_icons)
    implementation(activity)
    implementation(navigation)
  }

  //Compose Utils
  with(Deps.Coil) {
    implementation(coil)
    implementation(insets)
    implementation(swipe)
  }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
  kotlinOptions {
//        useIR = true
    freeCompilerArgs += listOf(
      "-Xopt-in=kotlinx.coroutines.InternalCoroutinesApi",
      "-Xopt-in=org.koin.core.component.KoinApiExtension",
      "-Xopt-in=androidx.compose.animation.ExperimentalAnimationApi",
      "-Xopt-in=androidx.compose.material.ExperimentalMaterialApi"
    )
//        "-P",
//        "plugin:androidx.compose.compiler.plugins.kotlin:suppressKotlinVersionCompatibilityCheck=true")
  }
}

android {
  compileSdk = Versions.compile_sdk
  defaultConfig {
    applicationId = "com.raywenderlich.timezone.android"
    minSdk = Versions.min_sdk
    targetSdk = Versions.target_sdk
    versionCode = 1
    versionName = "1.0"
  }
  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
//        sourceCompatibility = JavaVersion.VERSION_11
//        targetCompatibility = JavaVersion.VERSION_11
  }
  kotlinOptions {
    jvmTarget = "1.8"
//        jvmTarget = "11"
  }
  buildFeatures {
//    viewBinding = true
    compose = true
  }
  composeOptions {
    kotlinCompilerVersion = Versions.kotlin
    kotlinCompilerExtensionVersion = Versions.compose
  }

}