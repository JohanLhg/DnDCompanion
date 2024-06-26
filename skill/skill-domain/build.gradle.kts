plugins {
    alias(libs.plugins.dndcompanion.jvm.room)
}

dependencies {
    implementation(project(":ability:ability-domain"))
    implementation(project(":character:character-domain"))
}