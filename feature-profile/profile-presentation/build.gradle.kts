plugins {
    alias(libs.plugins.dndcompanion.android.feature.ui)
}

android {
    namespace = "com.jlahougue.profile_presentation"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(project(":core:core-domain"))
    implementation(project(":feature-profile:profile-domain"))
    implementation(project(":user-info:user-info-domain"))
    implementation(project(":character:character-domain"))
    implementation(project(":class:class-domain"))

    implementation(project(":character:character-presentation"))
    implementation(project(":class:class-presentation"))

    implementation(libs.bundles.coil)
}