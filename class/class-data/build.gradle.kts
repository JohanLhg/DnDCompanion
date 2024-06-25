plugins {
    alias(libs.plugins.dndcompanion.jvm.room)
}

dependencies {
    implementation(project(":core:core-data-interface"))
    implementation(project(":core:core-domain"))
    implementation(project(":class:class-domain"))
    implementation(project(":ability:ability-domain"))

    implementation(libs.json)
}