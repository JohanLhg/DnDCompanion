plugins {
    alias(libs.plugins.dndcompanion.android.feature.ui)
}

android {
    namespace = "com.jlahougue.character_selection_presentation"
}

dependencies {
    implementation(project(":core:core-domain"))
    implementation(project(":feature-character-selection:character-selection-domain"))
    implementation(project(":user-info:user-info-domain"))
    implementation(project(":character:character-domain"))
    implementation(project(":character-sheet:character-sheet-domain"))

    implementation(project(":character:character-presentation"))

    implementation(libs.bundles.coil)
}