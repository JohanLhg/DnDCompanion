plugins {
    alias(libs.plugins.dndcompanion.jvm.room)
}

dependencies {
    implementation(project(":spell:spell-domain"))
    implementation(project(":ability:ability-domain"))
    implementation(project(":character:character-domain"))
    implementation(project(":class:class-domain"))
    implementation(project(":damage-type:damage-type-domain"))
}