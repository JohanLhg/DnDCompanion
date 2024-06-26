plugins {
    alias(libs.plugins.dndcompanion.jvm.data)
}

dependencies {
    implementation(project(":weapon:weapon-domain"))
    implementation(project(":ability:ability-domain"))
    implementation(project(":character:character-domain"))

    implementation(libs.json)
}