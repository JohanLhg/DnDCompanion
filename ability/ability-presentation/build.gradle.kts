plugins {
    alias(libs.plugins.dndcompanion.android.feature.ui)
}

android {
    namespace = "com.jlahougue.ability_presentation"
}

dependencies {
    implementation(project(":core:core-domain"))
    implementation(project(":ability:ability-domain"))

}