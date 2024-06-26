plugins {
    alias(libs.plugins.dndcompanion.android.feature.ui)
}

android {
    namespace = "com.jlahougue.item_presentation"
}

dependencies {
    implementation(project(":core:core-domain"))
    implementation(project(":item:item-domain"))
    implementation(project(":money:money-domain"))

    implementation(project(":money:money-presentation"))
}