plugins {
    alias(libs.plugins.dndcompanion.android.feature.ui)
}

android {
    namespace = "com.jlahougue.equipment_presentation"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(project(":core:core-domain"))
    implementation(project(":feature-equipment:equipment-domain"))

    implementation(project(":user-info:user-info-domain"))
    implementation(project(":ability:ability-domain"))
    implementation(project(":item:item-domain"))
    implementation(project(":money:money-domain"))
    implementation(project(":weapon:weapon-domain"))
    implementation(project(":property:property-domain"))

    implementation(project(":item:item-presentation"))
    implementation(project(":money:money-presentation"))
    implementation(project(":weapon:weapon-presentation"))
    implementation(project(":property:property-presentation"))
}