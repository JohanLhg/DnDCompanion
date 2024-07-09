plugins {
    alias(libs.plugins.dndcompanion.android.feature.ui)
}

android {
    namespace = "com.jlahougue.class_presentation"
}

dependencies {
    implementation(project(":core:core-domain"))
    implementation(project(":ability:ability-presentation"))

    implementation(project(":class:class-domain"))
    implementation(project(":ability:ability-domain"))
}