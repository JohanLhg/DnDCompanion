plugins {
    alias(libs.plugins.dndcompanion.android.feature.ui)
}

android {
    namespace = "com.jlahougue.note.presentation"
}

dependencies {
    implementation(project(":note:domain"))
}