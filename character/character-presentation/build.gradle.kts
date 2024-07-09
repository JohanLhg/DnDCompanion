plugins {
    alias(libs.plugins.dndcompanion.android.feature.ui)
}

android {
    namespace = "com.jlahougue.character_presentation"
}

dependencies {
    implementation(project(":core:core-domain"))
    implementation(project(":character:character-domain"))

    implementation(libs.bundles.coil)
}