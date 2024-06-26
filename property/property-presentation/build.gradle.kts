plugins {
    alias(libs.plugins.dndcompanion.android.feature.ui)
}

android {
    namespace = "com.jlahougue.property_presentation"
}

dependencies {

    implementation(project(":property:property-domain"))
}