pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "DnDCompanion"
include(":app")
include(":core:core-domain")
include(":ability:ability-domain")
include(":character:character-domain")
include(":core:core-presentation")
include(":class:class-domain")
include(":money:money-domain")
include(":damage-type:damage-type-domain")
include(":health:health-domain")
include(":item:item-domain")
include(":property:property-domain")
include(":settings:settings-domain")
include(":skill:skill-domain")
include(":spell:spell-domain")
include(":stats:stats-domain")
include(":user-info:user-info-domain")
include(":weapon:weapon-domain")
include(":core:core-data-remote-instance")
include(":ability:ability-data")
include(":authentication:authentication-domain")
include(":authentication:authentication-data")
include(":character:character-data")
include(":character-spell:character-spell-domain")
include(":class:class-data")
include(":character-sheet:character-sheet-domain")
include(":character-sheet:character-sheet-data")
include(":spell:spell-data")
include(":user-info:user-info-data")
include(":damage-type:damage-type-data")
include(":property:property-data")
include(":character-spell:character-spell-data")
include(":money:money-data")
include(":health:health-data")
include(":item:item-data")
include(":skill:skill-data")
include(":stats:stats-data")
include(":weapon:weapon-data")
include(":core:core-di")
include(":core:core-data")
include(":ability:ability-presentation")
include(":character-spell:character-spell-presentation")
include(":money:money-presentation")
include(":health:health-presentation")
include(":skill:skill-presentation")
include(":stats:stats-presentation")
include(":item:item-presentation")
include(":weapon:weapon-presentation")
include(":feature-authentication:authentication-domain")
include(":feature-authentication:authentication-presentation")
include(":feature-character-selection:character-selection-domain")
include(":feature-character-selection:character-selection-presentation")
include(":feature-combat:combat-domain")
include(":feature-combat:combat-presentation")
include(":feature-equipment:equipment-domain")
include(":feature-equipment:equipment-presentation")
include(":feature-splash-screen:splash-screen-presentation")
include(":feature-spells:spells-domain")
include(":feature-spells:spells-presentation")
include(":feature-loading:loading-domain")
include(":feature-loading:loading-presentation")
include(":core:core-dependency-injection")
include(":feature-settings:settings-presentation")
include(":feature-settings:settings-domain")
include(":authentication:authentication-presentation")
include(":damage-type:damage-type-presentation")
include(":property:property-presentation")
include(":feature-profile:profile-presentation")
include(":feature-profile:profile-domain")
include(":character:character-presentation")
include(":class:class-presentation")
