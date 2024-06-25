plugins {
    alias(libs.plugins.dndcompanion.jvm.room)
}

dependencies {
    implementation(project(":core:core-data-interface"))
    implementation(project(":skill:skill-domain"))
    implementation(project(":ability:ability-domain"))
}