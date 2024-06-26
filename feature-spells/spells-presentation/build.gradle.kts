plugins {
    alias(libs.plugins.dndcompanion.android.feature.ui)
}

android {
    namespace = "com.jlahougue.spells_presentation"
}

dependencies {
    implementation(project(":core:core-domain"))
    implementation(project(":feature-spells:spells-domain"))

    implementation(project(":user-info:user-info-domain"))
    implementation(project(":character:character-domain"))
    implementation(project(":ability:ability-domain"))
    implementation(project(":damage-type:damage-type-domain"))
    implementation(project(":character-spell:character-spell-domain"))
    implementation(project(":spell:spell-domain"))
    implementation(project(":class:class-domain"))

    implementation(project(":character-spell:character-spell-presentation"))
    implementation(project(":damage-type:damage-type-presentation"))
    implementation(project(":ability:ability-presentation"))
}