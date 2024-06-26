plugins {
    alias(libs.plugins.dndcompanion.android.library)
}

android {
    namespace = "com.jlahougue.splash_screen_presentation"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(project(":authentication:authentication-domain"))

    implementation(libs.navigation)
}