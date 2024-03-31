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
    implementation(project(":character-sheet:character-sheet-domain"))
    implementation(project(":character:character-domain"))
    implementation(project(":ability:ability-domain"))
    implementation(project(":class:class-domain"))
    implementation(project(":damage-type:damage-type-domain"))
    implementation(project(":health:health-domain"))
    implementation(project(":item:item-domain"))
    implementation(project(":money:money-domain"))
    implementation(project(":character-spell:character-spell-domain"))
    implementation(project(":spell:spell-domain"))
    implementation(project(":property:property-domain"))
    implementation(project(":weapon:weapon-domain"))
    implementation(project(":stats:stats-domain"))
    implementation(project(":skill:skill-domain"))

    implementation(libs.kotlin.coroutines)
}