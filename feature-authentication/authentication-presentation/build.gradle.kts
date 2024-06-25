plugins {
    alias(libs.plugins.dndcompanion.android.feature.ui)
}

android {
    namespace = "com.jlahougue.feature.authentication_presentation"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(project(":authentication:authentication-presentation"))

    implementation(project(":core:core-domain"))
    implementation(project(":authentication:authentication-domain"))
    implementation(project(":feature-authentication:authentication-domain"))

}