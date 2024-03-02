plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.googleServices)
}

android {
    namespace = "com.jlahougue.dndcompanion"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.jlahougue.dndcompanion"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    packaging {
        resources.excludes.addAll(
            listOf(
                "META-INF/LICENSE-notice.md",
                "/META-INF/{AL2.0,LGPL2.1}",
                "META-INF/DEPENDENCIES",
                "META-INF/LICENSE",
                "META-INF/LICENSE.md",
                "META-INF/LICENSE.txt",
                "META-INF/license.txt",
                "META-INF/NOTICE",
                "META-INF/NOTICE.txt",
                "META-INF/notice.txt",
                "META-INF/ASL2.0",
                "META-INF/*.kotlin_module"
            )
        )
    }
}

dependencies {
    implementation(project(":core:core-data-remote-instance"))
    implementation(project(":core:core-data"))
    implementation(project(":core:core-di"))
    implementation(project(":core:core-domain"))
    implementation(project(":core:core-presentation"))

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

    implementation(project(":ability:ability-presentation"))
    implementation(project(":character-spell:character-spell-presentation"))
    implementation(project(":health:health-presentation"))
    implementation(project(":item:item-presentation"))
    implementation(project(":money:money-presentation"))
    implementation(project(":skill:skill-presentation"))
    implementation(project(":stats:stats-presentation"))
    implementation(project(":weapon:weapon-presentation"))

    // Compose
    implementation(libs.bundles.ui)

    // Datastore (local database)
    implementation(libs.bundles.datastore)

    // Room Database (local database)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    implementation(libs.bundles.coil)

    // Glide (image loading)
    implementation(libs.bundles.glide)

    // Firebase (remote database)
    implementation(libs.bundles.firebase)

    // API calls
    implementation(libs.bundles.network)

    // Local unit tests
    testImplementation(libs.bundles.tests)

    // Instrumented tests
    androidTestImplementation(libs.bundles.android.tests)
    debugImplementation(libs.compose.tooling)
}