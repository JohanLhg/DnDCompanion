plugins {
    alias(libs.plugins.dndcompanion.jvm.room)
}

dependencies {
    implementation(project(":core:core-data-interface"))
    implementation(project(":core:core-domain"))
    implementation(project(":weapon:weapon-domain"))
    implementation(project(":ability:ability-domain"))
    implementation(project(":character:character-domain"))

    implementation(libs.json)
}