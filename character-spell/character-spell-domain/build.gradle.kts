plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.jlahougue.character_spell_domain"
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
    implementation(project(":spell:spell-domain"))
    implementation(project(":ability:ability-domain"))
    implementation(project(":character:character-domain"))
    implementation(project(":class:class-domain"))
    implementation(project(":damage-type:damage-type-domain"))

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    testImplementation(libs.bundles.tests)
    androidTestImplementation(libs.bundles.android.tests)
}