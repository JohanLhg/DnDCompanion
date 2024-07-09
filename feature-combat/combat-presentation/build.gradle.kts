plugins {
    alias(libs.plugins.dndcompanion.android.feature.ui)
}

android {
    namespace = "com.jlahougue.combat_presentation"
}

dependencies {
    implementation(project(":core:core-domain"))
    implementation(project(":feature-combat:combat-domain"))

    implementation(project(":user-info:user-info-domain"))
    implementation(project(":ability:ability-domain"))
    implementation(project(":damage-type:damage-type-domain"))
    implementation(project(":stats:stats-domain"))
    implementation(project(":health:health-domain"))
    implementation(project(":character-spell:character-spell-domain"))
    implementation(project(":weapon:weapon-domain"))
    implementation(project(":property:property-domain"))
    implementation(project(":item:item-domain"))
    implementation(project(":money:money-domain"))

    implementation(project(":ability:ability-presentation"))
    implementation(project(":damage-type:damage-type-presentation"))
    implementation(project(":stats:stats-presentation"))
    implementation(project(":health:health-presentation"))
    implementation(project(":character-spell:character-spell-presentation"))
    implementation(project(":weapon:weapon-presentation"))
    implementation(project(":property:property-presentation"))
    implementation(project(":item:item-presentation"))
}