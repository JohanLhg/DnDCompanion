plugins {
    alias(libs.plugins.dndcompanion.jvm.room)
}

dependencies {
    implementation(project(":character:character-domain"))
}