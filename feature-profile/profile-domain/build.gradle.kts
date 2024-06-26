plugins {
    alias(libs.plugins.dndcompanion.jvm.library)
}

dependencies {
    implementation(project(":core:core-domain"))
    implementation(project(":character:character-domain"))
    implementation(project(":user-info:user-info-domain"))
    implementation(project(":class:class-domain"))
}