plugins {
    alias(libs.plugins.dndcompanion.jvm.library)
}

dependencies {
    implementation(project(":core:core-domain"))
    implementation(project(":character-sheet:character-sheet-domain"))
    implementation(project(":character:character-domain"))
    implementation(project(":ability:ability-domain"))
    implementation(project(":class:class-domain"))
    implementation(project(":damage-type:damage-type-domain"))
    implementation(project(":health:health-domain"))
    implementation(project(":item:item-domain"))
    implementation(project(":money:money-domain"))
    implementation(project(":character-spell:character-spell-domain"))
    implementation(project(":spell:spell-domain"))
    implementation(project(":property:property-domain"))
    implementation(project(":weapon:weapon-domain"))
    implementation(project(":stats:stats-domain"))
    implementation(project(":skill:skill-domain"))
}