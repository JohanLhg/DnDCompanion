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
    implementation(project(":ability:ability-domain"))
    implementation(project(":skill:skill-domain"))
    implementation(project(":health:health-domain"))
    implementation(project(":item:item-domain"))
}