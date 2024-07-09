plugins {
    alias(libs.plugins.dndcompanion.android.feature.ui)
}

android {
    namespace = "com.jlahougue.damage_type_presentation"
}

dependencies {
    implementation(project(":damage-type:damage-type-domain"))
}