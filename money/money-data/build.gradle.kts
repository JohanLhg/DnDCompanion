plugins {
    alias(libs.plugins.dndcompanion.jvm.data)
}

dependencies {
    implementation(project(":money:money-domain"))
}