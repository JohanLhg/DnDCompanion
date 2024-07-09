plugins {
    alias(libs.plugins.dndcompanion.android.feature.ui)
}

android {
    namespace = "com.jlahougue.loading_presentation"
}

dependencies {
    implementation(project(":core:core-domain"))
    implementation(project(":feature-loading:loading-domain"))
}