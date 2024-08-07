plugins {
    alias(libs.plugins.dndcompanion.android.feature.ui)
}

android {
    namespace = "com.jlahougue.weapon_presentation"
}

dependencies {
    implementation(project(":core:core-domain"))
    implementation(project(":weapon:weapon-domain"))
    implementation(project(":ability:ability-domain"))
    implementation(project(":property:property-domain"))
    implementation(project(":user-info:user-info-domain"))

    implementation(project(":ability:ability-presentation"))
}