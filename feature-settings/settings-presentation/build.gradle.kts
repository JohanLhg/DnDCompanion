plugins {
    alias(libs.plugins.dndcompanion.android.feature.ui)
}

android {
    namespace = "com.jlahougue.settings_presentation"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(project(":core:core-domain"))
    implementation(project(":feature-settings:settings-domain"))

    implementation(project(":authentication:authentication-domain"))
    implementation(project(":user-info:user-info-domain"))

    implementation(project(":authentication:authentication-presentation"))
}