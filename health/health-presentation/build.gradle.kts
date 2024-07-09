plugins {
    alias(libs.plugins.dndcompanion.android.feature.ui)
}

android {
    namespace = "com.jlahougue.health_presentation"
}

dependencies {
    implementation(project(":core:core-domain"))
    implementation(project(":health:health-domain"))
}