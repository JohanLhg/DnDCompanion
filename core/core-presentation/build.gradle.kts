plugins {
    alias(libs.plugins.dndcompanion.android.library.compose)
}

android {
    namespace = "com.jlahougue.core_presentation"
}

dependencies {
    implementation(libs.bundles.ui)

    implementation(project(":core:core-domain"))
}