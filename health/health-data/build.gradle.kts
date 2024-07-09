plugins {
    alias(libs.plugins.dndcompanion.jvm.data)
}

dependencies {
    implementation(project(":health:health-domain"))
    implementation(project(":character:character-domain"))
}