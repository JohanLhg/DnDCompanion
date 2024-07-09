plugins {
    alias(libs.plugins.dndcompanion.android.feature.ui)
}

android {
    namespace = "com.jlahougue.character_spell_presentation"
}

dependencies {
    implementation(project(":core:core-domain"))
    implementation(project(":character-spell:character-spell-domain"))
    implementation(project(":ability:ability-domain"))
    implementation(project(":ability:ability-presentation"))
    implementation(project(":class:class-domain"))
    implementation(project(":damage-type:damage-type-domain"))

    implementation(libs.compose.constraintlayout)
}