plugins {
    alias(libs.plugins.dndcompanion.jvm.data)
}

dependencies {
    implementation(project(":property:property-domain"))

    implementation(libs.json)
}