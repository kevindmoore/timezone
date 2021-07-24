plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(project(":shared"))

    implementation(Deps.napier)
    implementation(Deps.material)

    implementation(Deps.Ktor.androidCore)

    // SqlDelight
    implementation(Deps.SqlDelight.runtimeJdk)
    implementation(Deps.SqlDelight.driverAndroid)

    // Coroutines
    implementation(Deps.Coroutines.common)
    implementation(Deps.Coroutines.android)

    // Settings
    implementation(Deps.multiplatformSettings)

    // Koin
    implementation(Deps.Koin.core)
    implementation(Deps.Koin.android)
    implementation(Deps.Koin.compose)

    // AndroidX
    implementation(Deps.AndroidX.constraintlayout)
    implementation(Deps.AndroidX.appcompat)
    implementation(Deps.AndroidX.core_ktx)

    //Compose
    implementation(Deps.Compose.runtime)
    implementation(Deps.Compose.runtime_livedata)
    implementation(Deps.Compose.ui)
    implementation(Deps.Compose.tooling)
    implementation(Deps.Compose.foundation)
    implementation(Deps.Compose.foundationLayout)
    implementation(Deps.Compose.material)
    implementation(Deps.Compose.material_icons)
    implementation(Deps.Compose.activity)
    implementation(Deps.Compose.navigation)

    //Compose Utils
    implementation(Deps.Coil.coil)
    implementation(Deps.Coil.insets)
    implementation(Deps.Coil.swipe)

}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
//        useIR = true
        freeCompilerArgs += listOf("-Xopt-in=kotlinx.coroutines.InternalCoroutinesApi",
        "-Xopt-in=org.koin.core.component.KoinApiExtension",
        "-Xopt-in=androidx.compose.animation.ExperimentalAnimationApi",
        "-Xopt-in=androidx.compose.material.ExperimentalMaterialApi")
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
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }

}