plugins {
    alias(libs.plugins.dndcompanion.android.feature.ui)
}

android {
    namespace = "com.jlahougue.skill_presentation"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(project(":core:core-domain"))
    implementation(project(":skill:skill-domain"))
    implementation(project(":ability:ability-domain"))

    implementation(project(":ability:ability-presentation"))
}