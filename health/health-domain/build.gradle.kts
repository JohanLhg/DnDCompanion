plugins {
    alias(libs.plugins.dndcompanion.jvm.room)
}

dependencies {
    implementation(project(":core:core-domain"))
    implementation(project(":character:character-domain"))
}