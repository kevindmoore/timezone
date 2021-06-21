object Versions {
    const val min_sdk = 22
    const val target_sdk = 30
    const val compile_sdk = 30

    const val kotlin = "1.5.10"
    const val kotlin_gradle_plugin = "1.5.10"
//    const val kotlin_gradle_plugin = "1.5.20-M1"
//    const val android_gradle_plugin = "4.1.3"
//    const val android_gradle_plugin = "4.2.0"
    const val android_gradle_plugin = "7.1.0-alpha02"

    const val buildToolsVersion = "30.0.3"
    const val coroutines = "1.5.0-native-mt"
    const val kermit = "0.1.9"
    const val koin = "3.0.2"
    const val ktlint_gradle_plugin = "10.1.0"
    const val ktor = "1.6.0"
    const val junit = "4.13.2"
    const val material = "1.3.0"
    const val desugarJdkLibs = "1.1.5"
    const val multiplatformSettings = "0.7.7"
    const val robolectric = "4.5.1"
    const val sqlDelight = "1.5.0"
    const val stately = "1.1.7"
    const val kotlinxDateTime = "0.2.1"
    const val turbine = "0.5.1"
    const val compose= "1.0.0-beta08"
    const val compose_compiler= "1.5.10"
    const val activity_compose = "1.3.0-alpha08"
    const val coil= "0.8.1"
    const val datetime = "0.2.1"
    const val nav_compose = "2.4.0-alpha01"
    const val napier = "1.4.1"
    const val serialization = "1.5.0"
    const val junit5 = "1.5.10"
    const val frameworkName = "shared"


    object AndroidX {
        const val appcompat = "1.3.0"
        const val constraintlayout = "2.0.4"
        const val core = "1.5.0"
        const val lifecycle = "2.3.1"
        const val recyclerview = "1.2.0"
        const val swipeRefresh = "1.1.0"
        const val test = "1.3.0"
        const val test_ext = "1.1.2"
    }
}

object Deps {
    const val android_gradle_plugin = "com.android.tools.build:gradle:${Versions.android_gradle_plugin}"
    const val junit = "junit:junit:${Versions.junit}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val desugarJdkLibs = "com.android.tools:desugar_jdk_libs:${Versions.desugarJdkLibs}"
    const val kermit = "co.touchlab:kermit:${Versions.kermit}"
    const val multiplatformSettings = "com.russhwolf:multiplatform-settings:${Versions.multiplatformSettings}"
    const val multiplatformSettingsTest = "com.russhwolf:multiplatform-settings-test:${Versions.multiplatformSettings}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    const val stately = "co.touchlab:stately-common:${Versions.stately}"
    const val kotlinxDateTime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.kotlinxDateTime}"
    const val turbine = "app.cash.turbine:turbine:${Versions.turbine}"
    const val napier = "io.github.aakira:napier:${Versions.napier}"

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appcompat}"
        const val core_ktx = "androidx.core:core-ktx:${Versions.AndroidX.core}"
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.constraintlayout}"
        const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.AndroidX.recyclerview}"
        const val swipeRefresh = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.AndroidX.swipeRefresh}"

        const val lifecycle_runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.AndroidX.lifecycle}"
        const val lifecycle_viewmodel = "androidx.lifecycle:lifecycle-viewmodel:${Versions.AndroidX.lifecycle}"
        const val lifecycle_viewmodel_extensions = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidX.lifecycle}"
        const val lifecycle_livedata = "androidx.lifecycle:lifecycle-livedata:${Versions.AndroidX.lifecycle}"
    }

    object Koin {
        val core = "io.insert-koin:koin-core:${Versions.koin}"
        val test = "io.insert-koin:koin-test:${Versions.koin}"
        val android = "io.insert-koin:koin-android:${Versions.koin}"
        val compose = "io.insert-koin:koin-androidx-compose:3.0.1"
    }


    object AndroidXTest {
        const val core = "androidx.test:core:${Versions.AndroidX.test}"
        const val junit = "androidx.test.ext:junit:${Versions.AndroidX.test_ext}"
        const val runner = "androidx.test:runner:${Versions.AndroidX.test}"
        const val rules = "androidx.test:rules:${Versions.AndroidX.test}"
    }

    object Compose {
        const val ui = "androidx.compose.ui:ui:${Versions.compose}"
        const val tooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        const val foundation = "androidx.compose.foundation:foundation:${Versions.compose}"
        const val material = "androidx.compose.material:material:${Versions.compose}"
        const val material_icons = "androidx.compose.material:material-icons-extended:${Versions.compose}"
        const val runtime = "androidx.compose.runtime:runtime:${Versions.compose}"
        const val compiler = "androidx.compose.compiler:compiler:${Versions.compose}"
        const val runtime_livedata = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
        const val foundationLayout = "androidx.compose.foundation:foundation-layout:${Versions.compose}"
        const val navigation = "androidx.navigation:navigation-compose:${Versions.nav_compose}"
        const val activity = "androidx.activity:activity-compose:${Versions.activity_compose}"
    }

    object Coil {
        const val coil = "com.google.accompanist:accompanist-coil:${Versions.coil}"
        const val insets = "com.google.accompanist:accompanist-insets:${Versions.coil}"
        const val swipe = "com.google.accompanist:accompanist-swiperefresh:${Versions.coil}"

    }
    object KotlinTest {
        const val common = "org.jetbrains.kotlin:kotlin-test-common:${Versions.kotlin}"
        const val annotations = "org.jetbrains.kotlin:kotlin-test-annotations-common:${Versions.kotlin}"
        const val jvm = "org.jetbrains.kotlin:kotlin-test:${Versions.kotlin}"
        const val junit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
        const val junit5framework = "org.jetbrains.kotlin:kotlin-test-framework-impl:${Versions.junit5}"
        const val junit5 = "org.jetbrains.kotlin:kotlin-test-junit5:${Versions.junit5}"
    }

    object Coroutines {
        const val common = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    }

    object SqlDelight {
        const val gradle = "com.squareup.sqldelight:gradle-plugin:${Versions.sqlDelight}"
        const val runtime = "com.squareup.sqldelight:runtime:${Versions.sqlDelight}"
        const val coroutinesExtensions = "com.squareup.sqldelight:coroutines-extensions:${Versions.sqlDelight}"
        const val runtimeJdk = "com.squareup.sqldelight:runtime-jvm:${Versions.sqlDelight}"
        const val driverIos = "com.squareup.sqldelight:native-driver:${Versions.sqlDelight}"
        const val driverAndroid = "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}"
    }

    object JetBrains {
        const val datetime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.datetime}"
    }

    object Ktor {
        const val commonCore = "io.ktor:ktor-client-core:${Versions.ktor}"
        const val commonJson = "io.ktor:ktor-client-json:${Versions.ktor}"
        const val commonLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
        const val androidCore = "io.ktor:ktor-client-okhttp:${Versions.ktor}"
        const val ios = "io.ktor:ktor-client-ios:${Versions.ktor}"
        const val commonSerialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"
    }
}
