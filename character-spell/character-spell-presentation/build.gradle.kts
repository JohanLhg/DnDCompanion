plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.jlahougue.character_spell_presentation"
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
    implementation(project(":core:core-presentation"))
    implementation(project(":core:core-domain"))
    implementation(project(":character-spell:character-spell-domain"))
    implementation(project(":ability:ability-domain"))
    implementation(project(":class:class-domain"))
    implementation(project(":damage-type:damage-type-domain"))

    implementation(libs.bundles.ui)

    testImplementation(libs.bundles.tests)
    androidTestImplementation(libs.bundles.android.tests)
    debugImplementation(libs.compose.tooling)
}