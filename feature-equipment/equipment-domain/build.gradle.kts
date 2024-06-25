plugins {
    alias(libs.plugins.dndcompanion.jvm.library)
}

dependencies {
    implementation(project(":core:core-domain"))
    implementation(project(":user-info:user-info-domain"))
    implementation(project(":item:item-domain"))
    implementation(project(":money:money-domain"))
    implementation(project(":weapon:weapon-domain"))
}