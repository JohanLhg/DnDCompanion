plugins {
    alias(libs.plugins.dndcompanion.android.feature.ui)
}

android {
    namespace = "com.jlahougue.roaming.presentation"
}

dependencies {
    implementation(project(":core:core-domain"))

    implementation(project(":feature-roaming:domain"))
    implementation(project(":user-info:user-info-domain"))
    implementation(project(":character-sheet:character-sheet-domain"))
    implementation(project(":ability:ability-domain"))
    implementation(project(":skill:skill-domain"))
    implementation(project(":health:health-domain"))
    implementation(project(":item:item-domain"))
    implementation(project(":money:money-domain"))
    implementation(project(":note:domain"))

    implementation(project(":health:health-presentation"))
    implementation(project(":ability:ability-presentation"))
    implementation(project(":skill:skill-presentation"))
    implementation(project(":item:item-presentation"))
    implementation(project(":note:presentation"))
}