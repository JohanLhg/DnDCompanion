plugins {
    alias(libs.plugins.dndcompanion.android.library)
    alias(libs.plugins.kotlinSerialization)
}

android {
    namespace = "com.jlahougue.user_info_data"
}

dependencies {
    implementation(project(":core:core-domain"))
    implementation(project(":user-info:user-info-domain"))

    implementation(libs.bundles.datastore)
}