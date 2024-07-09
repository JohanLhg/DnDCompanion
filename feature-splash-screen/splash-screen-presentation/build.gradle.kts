plugins {
    alias(libs.plugins.dndcompanion.android.library)
}

android {
    namespace = "com.jlahougue.splash_screen_presentation"
}

dependencies {
    implementation(project(":authentication:authentication-domain"))

    implementation(libs.navigation)
}