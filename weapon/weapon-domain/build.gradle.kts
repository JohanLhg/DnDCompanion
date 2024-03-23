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
    implementation(project(":ability:ability-domain"))
    implementation(project(":property:property-domain"))
    implementation(project(":user-info:user-info-domain"))

    implementation(libs.kotlin.coroutines)
    implementation(libs.room.common)
}