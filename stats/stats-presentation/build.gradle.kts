plugins {
    alias(libs.plugins.dndcompanion.android.feature.ui)
}

android {
    namespace = "com.jlahougue.stats_presentation"
}

dependencies {
    implementation(project(":core:core-domain"))
    implementation(project(":stats:stats-domain"))
}