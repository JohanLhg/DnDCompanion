plugins {
    alias(libs.plugins.dndcompanion.jvm.data)
}

dependencies {
    implementation(project(":note:domain"))
}