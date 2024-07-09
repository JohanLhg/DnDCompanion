plugins {
    alias(libs.plugins.dndcompanion.android.application.compose)
    alias(libs.plugins.googleServices)
}

android {
    namespace = "com.jlahougue.dndcompanion"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    implementation(project(":core:core-data-interface"))
    implementation(project(":core:core-data"))
    implementation(project(":core:core-domain"))
    implementation(project(":core:core-presentation"))
    implementation(project(":core:core-dependency-injection"))

    implementation(project(":authentication:authentication-data"))
    implementation(project(":authentication:authentication-domain"))
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

    implementation(project(":ability:ability-presentation"))
    implementation(project(":character-spell:character-spell-presentation"))
    implementation(project(":health:health-presentation"))
    implementation(project(":item:item-presentation"))
    implementation(project(":money:money-presentation"))
    implementation(project(":skill:skill-presentation"))
    implementation(project(":stats:stats-presentation"))
    implementation(project(":weapon:weapon-presentation"))

    implementation(project(":feature-authentication:authentication-domain"))
    implementation(project(":feature-character-selection:character-selection-domain"))
    implementation(project(":feature-combat:combat-domain"))
    implementation(project(":feature-roaming:domain"))
    implementation(project(":feature-equipment:equipment-domain"))
    implementation(project(":feature-profile:profile-domain"))
    implementation(project(":feature-settings:settings-domain"))
    implementation(project(":feature-spells:spells-domain"))
    implementation(project(":feature-loading:loading-domain"))

    implementation(project(":feature-authentication:authentication-presentation"))
    implementation(project(":feature-character-selection:character-selection-presentation"))
    implementation(project(":feature-combat:combat-presentation"))
    implementation(project(":feature-roaming:presentation"))
    implementation(project(":feature-equipment:equipment-presentation"))
    implementation(project(":feature-profile:profile-presentation"))
    implementation(project(":feature-splash-screen:splash-screen-presentation"))
    implementation(project(":feature-settings:settings-presentation"))
    implementation(project(":feature-spells:spells-presentation"))
    implementation(project(":feature-loading:loading-presentation"))

    // Compose
    implementation(libs.bundles.ui)

    implementation(libs.bundles.coil)

    // Glide (image loading)
    implementation(libs.bundles.glide)

    testImplementation(libs.test.coroutines)
}