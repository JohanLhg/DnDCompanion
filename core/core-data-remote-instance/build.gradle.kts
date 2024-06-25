plugins {
    alias(libs.plugins.dndcompanion.android.library)
}

android {
    namespace = "com.jlahougue.core_data_remote_instance"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(project(":core:core-domain"))
    implementation(project(":core:core-data-interface"))

    implementation(libs.bundles.firebase)
    implementation(libs.bundles.network)
}