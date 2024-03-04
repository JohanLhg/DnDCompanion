plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.jlahougue.combat_presentation"
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
}

dependencies {
    implementation(project(":core:core-domain"))
    implementation(project(":core:core-presentation"))
    implementation(project(":feature-combat:combat-domain"))

    implementation(project(":user-info:user-info-domain"))
    implementation(project(":settings:settings-domain"))
    implementation(project(":ability:ability-domain"))
    implementation(project(":stats:stats-domain"))
    implementation(project(":health:health-domain"))
    implementation(project(":character-spell:character-spell-domain"))
    implementation(project(":weapon:weapon-domain"))
    implementation(project(":item:item-domain"))
    implementation(project(":money:money-domain"))

    implementation(project(":ability:ability-presentation"))
    implementation(project(":stats:stats-presentation"))
    implementation(project(":health:health-presentation"))
    implementation(project(":character-spell:character-spell-presentation"))
    implementation(project(":weapon:weapon-presentation"))
    implementation(project(":item:item-presentation"))

    implementation(libs.bundles.ui)

    testImplementation(libs.bundles.tests)
    androidTestImplementation(libs.bundles.android.tests)
    debugImplementation(libs.compose.tooling)
}