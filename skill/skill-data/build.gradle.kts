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
    implementation(project(":skill:skill-domain"))
    implementation(project(":ability:ability-domain"))

    implementation(libs.kotlin.coroutines)
    implementation(libs.room.common)
}