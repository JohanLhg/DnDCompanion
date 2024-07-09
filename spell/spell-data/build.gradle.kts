plugins {
    alias(libs.plugins.dndcompanion.jvm.data)
}

dependencies {
    implementation(project(":spell:spell-domain"))

    implementation(libs.json)
}