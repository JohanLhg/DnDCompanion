plugins {
    alias(libs.plugins.dndcompanion.jvm.data)
}

dependencies {
    implementation(project(":damage-type:damage-type-domain"))

    implementation(libs.json)
}