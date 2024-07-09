plugins {
    alias(libs.plugins.dndcompanion.jvm.data)
}

dependencies {
    implementation(project(":class:class-domain"))
    implementation(project(":ability:ability-domain"))

    implementation(libs.json)
}