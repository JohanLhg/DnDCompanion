plugins {
    alias(libs.plugins.dndcompanion.jvm.room)
}

dependencies {
    implementation(project(":core:core-domain"))
    implementation(project(":user-info:user-info-domain"))
    implementation(project(":character-sheet:character-sheet-domain"))
}