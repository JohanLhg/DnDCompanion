plugins {
    alias(libs.plugins.dndcompanion.jvm.library)
}

dependencies {
    implementation(project(":core:core-domain"))
    implementation(project(":user-info:user-info-domain"))
    implementation(project(":ability:ability-domain"))
    implementation(project(":stats:stats-domain"))
    implementation(project(":health:health-domain"))
    implementation(project(":character-spell:character-spell-domain"))
    implementation(project(":weapon:weapon-domain"))
    implementation(project(":item:item-domain"))
}