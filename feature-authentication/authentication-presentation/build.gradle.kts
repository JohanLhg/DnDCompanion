plugins {
    alias(libs.plugins.dndcompanion.android.feature.ui)
}

android {
    namespace = "com.jlahougue.feature.authentication_presentation"
}

dependencies {
    implementation(project(":authentication:authentication-presentation"))

    implementation(project(":core:core-domain"))
    implementation(project(":authentication:authentication-domain"))
    implementation(project(":feature-authentication:authentication-domain"))

}