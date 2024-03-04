plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.jlahougue.character_sheet_domain"
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

    implementation(project(":ability:ability-domain"))
    implementation(project(":character:character-domain"))
    implementation(project(":money:money-domain"))
    implementation(project(":health:health-domain"))
    implementation(project(":item:item-domain"))
    implementation(project(":skill:skill-domain"))
    implementation(project(":character-spell:character-spell-domain"))
    implementation(project(":stats:stats-domain"))
    implementation(project(":weapon:weapon-domain"))

    implementation(libs.lifecycle)

    testImplementation(libs.bundles.tests)
    androidTestImplementation(libs.bundles.android.tests)
}