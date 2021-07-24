pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "Timezone"
include(":timezoneAndroid")
include(":timezone-desktop")
include(":compose-common")
include(":shared")