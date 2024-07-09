plugins {
    alias(libs.plugins.dndcompanion.android.feature.ui)
}

android {
    namespace = "com.jlahougue.settings_presentation"
}

dependencies {
    implementation(project(":core:core-domain"))
    implementation(project(":feature-settings:settings-domain"))

    implementation(project(":authentication:authentication-domain"))
    implementation(project(":user-info:user-info-domain"))

    implementation(project(":authentication:authentication-presentation"))
}