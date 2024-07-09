plugins {
    alias(libs.plugins.dndcompanion.android.library)
}

android {
    namespace = "com.jlahougue.authentication_data"
}

dependencies {
    implementation(project(":core:core-domain"))
    implementation(project(":authentication:authentication-domain"))
    implementation(project(":user-info:user-info-domain"))
    implementation(project(":character-sheet:character-sheet-domain"))

    implementation(libs.firebase.auth)
}