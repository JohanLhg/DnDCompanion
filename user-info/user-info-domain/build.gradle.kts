plugins {
    alias(libs.plugins.dndcompanion.jvm.library)
    alias(libs.plugins.kotlinSerialization)
}

dependencies {
    implementation(project(":core:core-domain"))

    implementation(libs.serialization)
}