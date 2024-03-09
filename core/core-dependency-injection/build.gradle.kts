plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.jlahougue.core_dependency_injection"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":core:core-di"))
    implementation(project(":core:core-data"))
    implementation(project(":core:core-domain"))

    implementation(project(":authentication:authentication-domain"))
    implementation(project(":settings:settings-domain"))
    implementation(project(":user-info:user-info-domain"))

    implementation(project(":character-sheet:character-sheet-domain"))
    implementation(project(":ability:ability-domain"))
    implementation(project(":character:character-domain"))
    implementation(project(":class:class-domain"))
    implementation(project(":money:money-domain"))
    implementation(project(":damage-type:damage-type-domain"))
    implementation(project(":health:health-domain"))
    implementation(project(":item:item-domain"))
    implementation(project(":property:property-domain"))
    implementation(project(":skill:skill-domain"))
    implementation(project(":character-spell:character-spell-domain"))
    implementation(project(":spell:spell-domain"))
    implementation(project(":stats:stats-domain"))
    implementation(project(":weapon:weapon-domain"))

    implementation(project(":authentication:authentication-data"))
    implementation(project(":character-sheet:character-sheet-data"))
    implementation(project(":ability:ability-data"))
    implementation(project(":character:character-data"))
    implementation(project(":class:class-data"))
    implementation(project(":money:money-data"))
    implementation(project(":health:health-data"))
    implementation(project(":item:item-data"))
    implementation(project(":damage-type:damage-type-data"))
    implementation(project(":property:property-data"))
    implementation(project(":spell:spell-data"))
    implementation(project(":character-spell:character-spell-data"))
    implementation(project(":stats:stats-data"))
    implementation(project(":skill:skill-data"))
    implementation(project(":weapon:weapon-data"))
    implementation(project(":user-info:user-info-data"))

    implementation(project(":feature-authentication:authentication-domain"))
    implementation(project(":feature-character-selection:character-selection-domain"))
    implementation(project(":feature-combat:combat-domain"))
    implementation(project(":feature-equipment:equipment-domain"))
    implementation(project(":feature-spells:spells-domain"))
    implementation(project(":feature-loading:loading-domain"))

    testImplementation(libs.bundles.tests)
    androidTestImplementation(libs.bundles.android.tests)
    debugImplementation(libs.compose.tooling)
}