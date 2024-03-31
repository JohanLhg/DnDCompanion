plugins {
    id("java-library")
    alias(libs.plugins.kotlinJvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(project(":core:core-data-interface"))
    implementation(project(":core:core-domain"))
    implementation(project(":weapon:weapon-domain"))
    implementation(project(":ability:ability-domain"))
    implementation(project(":character:character-domain"))

    implementation(libs.kotlin.coroutines)
    implementation(libs.room.common)
    implementation(libs.json)
}