plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.jlahougue.core_di"
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
    implementation(project(":core:core-domain"))

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

    testImplementation(libs.bundles.tests)
    androidTestImplementation(libs.bundles.android.tests)
}