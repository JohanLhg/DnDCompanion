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
    implementation(project(":character-sheet:character-sheet-domain"))

    implementation(project(":ability:ability-domain"))
    implementation(project(":character:character-domain"))
    implementation(project(":money:money-domain"))
    implementation(project(":health:health-domain"))
    implementation(project(":item:item-domain"))
    implementation(project(":skill:skill-domain"))
    implementation(project(":character-spell:character-spell-domain"))
    implementation(project(":stats:stats-domain"))
    implementation(project(":weapon:weapon-domain"))
}