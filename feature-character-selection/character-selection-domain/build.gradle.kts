plugins {
    id("java-library")
    alias(libs.plugins.kotlinJvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(project(":core:core-domain"))
    implementation(project(":user-info:user-info-domain"))
    implementation(project(":character:character-domain"))
    implementation(project(":character-sheet:character-sheet-domain"))
}