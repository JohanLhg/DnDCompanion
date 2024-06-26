plugins {
    alias(libs.plugins.dndcompanion.android.feature.ui)
}

android {
    namespace = "com.jlahougue.money_presentation"
}

dependencies {
    implementation(project(":core:core-domain"))
    implementation(project(":money:money-domain"))
}