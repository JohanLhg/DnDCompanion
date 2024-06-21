package com.jlahougue.core_dependency_injection

import android.app.Application
import com.jlahougue.ability_data.AbilityModule
import com.jlahougue.authentication_data.AuthModule
import com.jlahougue.character_data.CharacterModule
import com.jlahougue.character_selection_domain.CharacterSelectionModule
import com.jlahougue.character_sheet_data.CharacterSheetModule
import com.jlahougue.character_spell_data.CharacterSpellModule
import com.jlahougue.class_data.ClassModule
import com.jlahougue.combat_domain.CombatModule
import com.jlahougue.core_data.di.AppModule
import com.jlahougue.core_data.di.DataSourceModule
import com.jlahougue.damage_type_data.DamageTypeModule
import com.jlahougue.equipment_domain.EquipmentModule
import com.jlahougue.feature.authentication_domain.AuthenticationModule
import com.jlahougue.feature.settings_domain.SettingsModule
import com.jlahougue.health_data.HealthModule
import com.jlahougue.item_data.ItemModule
import com.jlahougue.loading_domain.di.LoadingModule
import com.jlahougue.money_data.MoneyModule
import com.jlahougue.profile_domain.ProfileModule
import com.jlahougue.property_data.PropertyModule
import com.jlahougue.roaming.domain.RoamingModule
import com.jlahougue.skill_data.SkillModule
import com.jlahougue.spell_data.SpellModule
import com.jlahougue.spells_domain.SpellsModule
import com.jlahougue.stats_data.StatsModule
import com.jlahougue.user_info_data.UserInfoModule
import com.jlahougue.weapon_data.WeaponModule

class DnDCompanionApp: Application() {

    companion object {
        lateinit var appModule: AppModule
        lateinit var dataSourceModule: DataSourceModule

        lateinit var userInfoModule: UserInfoModule

        lateinit var authModule: AuthModule
        lateinit var characterModule: CharacterModule
        lateinit var healthModule: HealthModule
        lateinit var abilityModule: AbilityModule
        lateinit var skillModule: SkillModule
        lateinit var statsModule: StatsModule
        lateinit var moneyModule: MoneyModule
        lateinit var itemModule: ItemModule
        lateinit var classModule: ClassModule
        lateinit var damageTypeModule: DamageTypeModule
        lateinit var characterSpellModule: CharacterSpellModule
        lateinit var spellModule: SpellModule
        lateinit var propertyModule: PropertyModule

        lateinit var characterSheetModule: CharacterSheetModule

        lateinit var weaponModule: WeaponModule
        lateinit var authenticationModule: AuthenticationModule
        lateinit var loadingModule: LoadingModule
        lateinit var characterSelectionModule: CharacterSelectionModule
        lateinit var combatModule: CombatModule
        lateinit var roamingModule: RoamingModule
        lateinit var settingsModule: SettingsModule
        lateinit var spellsModule: SpellsModule
        lateinit var equipmentModule: EquipmentModule
        lateinit var profileModule: ProfileModule
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModule()
        dataSourceModule = DataSourceModule(this)

        userInfoModule = UserInfoModule(
            this,
            appModule.dispatcherProvider
        )

        characterModule = CharacterModule(
            appModule.dispatcherProvider,
            dataSourceModule.firebaseDataSource,
            dataSourceModule.roomDataSource.characterDao()
        )
        healthModule = HealthModule(
            appModule.dispatcherProvider,
            dataSourceModule.firebaseDataSource,
            dataSourceModule.roomDataSource.healthDao()
        )
        abilityModule = AbilityModule(
            appModule.dispatcherProvider,
            dataSourceModule.firebaseDataSource,
            dataSourceModule.roomDataSource.abilityDao()
        )
        skillModule = SkillModule(
            dataSourceModule.firebaseDataSource,
            dataSourceModule.roomDataSource.skillDao()
        )
        statsModule = StatsModule(
            appModule.dispatcherProvider,
            dataSourceModule.firebaseDataSource,
            dataSourceModule.roomDataSource.statsDao()
        )
        moneyModule = MoneyModule(
            appModule.dispatcherProvider,
            dataSourceModule.firebaseDataSource,
            dataSourceModule.roomDataSource.moneyDao()
        )
        itemModule = ItemModule(
            appModule.dispatcherProvider,
            dataSourceModule.firebaseDataSource,
            dataSourceModule.roomDataSource.itemDao()
        )
        classModule = ClassModule(
            appModule.dispatcherProvider,
            dataSourceModule.apiDataSource,
            dataSourceModule.roomDataSource.classDao()
        )
        damageTypeModule = DamageTypeModule(
            appModule.dispatcherProvider,
            dataSourceModule.apiDataSource,
            dataSourceModule.roomDataSource.damageTypeDao()
        )
        characterSpellModule = CharacterSpellModule(
            appModule.dispatcherProvider,
            dataSourceModule.firebaseDataSource,
            dataSourceModule.roomDataSource.characterSpellDao()
        )
        spellModule = SpellModule(
            appModule.dispatcherProvider,
            dataSourceModule.apiDataSource,
            dataSourceModule.roomDataSource.spellDao()
        )
        propertyModule = PropertyModule(
            appModule.dispatcherProvider,
            dataSourceModule.apiDataSource,
            dataSourceModule.roomDataSource.propertyDao()
        )
        weaponModule = WeaponModule(
            appModule.dispatcherProvider,
            dataSourceModule.firebaseDataSource,
            dataSourceModule.apiDataSource,
            dataSourceModule.roomDataSource.weaponDao()
        )

        characterSheetModule = CharacterSheetModule(
            appModule.dispatcherProvider,
            dataSourceModule.firebaseDataSource,
            characterModule.repository,
            healthModule.repository,
            abilityModule.repository,
            skillModule.repository,
            statsModule.repository,
            moneyModule.repository,
            itemModule.repository,
            characterSpellModule.repository,
            weaponModule.repository
        )
        authModule = AuthModule(
            appModule.dispatcherProvider,
            dataSourceModule.authDataSource,
            userInfoModule.useCases,
            characterSheetModule.useCases
        )

        authenticationModule = AuthenticationModule(
            appModule.dispatcherProvider,
            authModule.useCases
        )
        loadingModule = LoadingModule(
            appModule.dispatcherProvider,
            characterSheetModule.repository,
            characterSheetModule.useCases,
            characterModule.repository,
            abilityModule.repository,
            skillModule.repository,
            classModule.repository,
            statsModule.repository,
            healthModule.repository,
            damageTypeModule.repository,
            characterSpellModule.repository,
            spellModule.repository,
            propertyModule.repository,
            weaponModule.repository,
            moneyModule.repository,
            itemModule.repository
        )
        characterSelectionModule = CharacterSelectionModule(
            appModule.dispatcherProvider,
            userInfoModule.useCases,
            characterModule.useCases,
            characterSheetModule.useCases
        )
        combatModule = CombatModule(
            appModule.dispatcherProvider,
            userInfoModule.useCases,
            abilityModule.useCases,
            statsModule.useCases,
            healthModule.useCases,
            characterSpellModule.useCases,
            weaponModule.useCases,
            itemModule.useCases
        )
        roamingModule = RoamingModule(
            appModule.dispatcherProvider,
            userInfoModule.useCases,
            characterSheetModule.useCases,
            abilityModule.useCases,
            skillModule.useCases,
            healthModule.useCases,
            itemModule.useCases
        )
        settingsModule = SettingsModule(
            appModule.dispatcherProvider,
            authModule.useCases,
            userInfoModule.useCases
        )
        spellsModule = SpellsModule(
            appModule.dispatcherProvider,
            spellModule.repository,
            userInfoModule.useCases,
            characterSpellModule.useCases,
            classModule.useCases,
            characterModule.useCases
        )
        equipmentModule = EquipmentModule(
            appModule.dispatcherProvider,
            userInfoModule.useCases,
            weaponModule.useCases,
            moneyModule.useCases,
            itemModule.useCases
        )
        profileModule = ProfileModule(
            appModule.dispatcherProvider,
            userInfoModule.useCases,
            characterModule.useCases,
            classModule.useCases
        )
    }
}