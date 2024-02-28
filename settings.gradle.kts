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
