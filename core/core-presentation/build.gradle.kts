plugins {
    alias(libs.plugins.dndcompanion.android.library.compose)
}

android {
    namespace = "com.jlahougue.core_presentation"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(libs.bundles.ui)

    implementation(project(":core:core-domain"))
}