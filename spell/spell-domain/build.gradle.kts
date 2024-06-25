plugins {
    alias(libs.plugins.dndcompanion.jvm.room)
}

dependencies {
    implementation(project(":core:core-domain"))
    implementation(project(":class:class-domain"))
}