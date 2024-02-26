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
