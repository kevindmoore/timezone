import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform") // kotlin("jvm") doesn't work well in IDEA/AndroidStudio (https://github.com/JetBrains/compose-jb/issues/22)
    id("org.jetbrains.compose")
}

kotlin {
    jvm {}
    sourceSets {
        named("jvmMain") {
            kotlin.srcDirs("src/jvmMain/kotlin")
            resources.srcDirs("src/resources")
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(project(":compose-common"))
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "com.raywenderlich.timezone.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "TimeZone"
            packageVersion = "1.0.0"

            windows {
                menu = true
                // see https://wixtoolset.org/documentation/manual/v3/howtos/general/generate_guids.html
                upgradeUuid = "1ECF9E52-E8E5-4DBA-A4E1-23AEF1FDB5A8"
            }

            macOS {
                // Use -Pcompose.desktop.mac.sign=true to sign and notarize.
                bundleID = "com.raywenderlich.timezone"
            }
        }
    }
}
