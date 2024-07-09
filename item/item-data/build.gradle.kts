plugins {
    alias(libs.plugins.dndcompanion.jvm.data)
}

dependencies {
    implementation(project(":item:item-domain"))
    implementation(project(":money:money-domain"))
}