plugins {
    alias(libs.plugins.dndcompanion.android.room)
}

android {
    namespace = "com.jlahougue.core_data"
}

dependencies {
    implementation(project(":core:core-data-remote-instance"))
    implementation(project(":core:core-data-interface"))
    implementation(project(":core:core-domain"))

    implementation(project(":ability:ability-domain"))
    implementation(project(":character-spell:character-spell-domain"))
    implementation(project(":character:character-domain"))
    implementation(project(":class:class-domain"))
    implementation(project(":damage-type:damage-type-domain"))
    implementation(project(":health:health-domain"))
    implementation(project(":item:item-domain"))
    implementation(project(":money:money-domain"))
    implementation(project(":note:domain"))
    implementation(project(":property:property-domain"))
    implementation(project(":skill:skill-domain"))
    implementation(project(":spell:spell-domain"))
    implementation(project(":stats:stats-domain"))
    implementation(project(":weapon:weapon-domain"))

    implementation(project(":ability:ability-data"))
    implementation(project(":character-spell:character-spell-data"))
    implementation(project(":character:character-data"))
    implementation(project(":class:class-data"))
    implementation(project(":damage-type:damage-type-data"))
    implementation(project(":health:health-data"))
    implementation(project(":item:item-data"))
    implementation(project(":money:money-data"))
    implementation(project(":note:data"))
    implementation(project(":property:property-data"))
    implementation(project(":skill:skill-data"))
    implementation(project(":spell:spell-data"))
    implementation(project(":stats:stats-data"))
    implementation(project(":weapon:weapon-data"))

    implementation(libs.firebase.auth)
    implementation(libs.okhttp)
    implementation(libs.junit.ktx)
}