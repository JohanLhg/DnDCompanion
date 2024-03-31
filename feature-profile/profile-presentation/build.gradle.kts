plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.jlahougue.profile_presentation"
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
    implementation(project(":feature-profile:profile-domain"))
    implementation(project(":user-info:user-info-domain"))
    implementation(project(":character:character-domain"))
    implementation(project(":class:class-domain"))

    implementation(project(":core:core-presentation"))
    implementation(project(":character:character-presentation"))
    implementation(project(":class:class-presentation"))

    implementation(libs.bundles.ui)
    implementation(libs.bundles.coil)

    testImplementation(libs.bundles.tests)
    androidTestImplementation(libs.bundles.android.tests)
    debugImplementation(libs.compose.tooling)
}