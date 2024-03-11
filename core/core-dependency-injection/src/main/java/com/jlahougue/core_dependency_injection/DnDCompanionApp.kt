package com.jlahougue.core_dependency_injection

import android.app.Application
import com.jlahougue.ability_data.di.AbilityModule
import com.jlahougue.authentication_data.di.AuthModule
import com.jlahougue.authentication_domain.AuthenticationModule
import com.jlahougue.character_data.di.CharacterModule
import com.jlahougue.character_selection_domain.CharacterSelectionModule
import com.jlahougue.character_sheet_data.di.CharacterSheetModule
import com.jlahougue.character_spell_data.di.CharacterSpellModule
import com.jlahougue.class_data.di.ClassModule
import com.jlahougue.combat_domain.CombatModule
import com.jlahougue.core_data.di.AppModule
import com.jlahougue.core_data.di.DataSourceModule
import com.jlahougue.damage_type_data.di.DamageTypeModule
import com.jlahougue.equipment_domain.EquipmentModule
import com.jlahougue.feature.settings_domain.SettingsModule
import com.jlahougue.health_data.di.HealthModule
import com.jlahougue.item_data.di.ItemModule
import com.jlahougue.loading_domain.di.LoadingModule
import com.jlahougue.money_data.di.MoneyModule
import com.jlahougue.property_data.di.PropertyModule
import com.jlahougue.skill_data.di.SkillModule
import com.jlahougue.spell_data.di.SpellModule
import com.jlahougue.spells_domain.SpellsModule
import com.jlahougue.stats_data.di.StatsModule
import com.jlahougue.user_info_data.di.UserInfoModule
import com.jlahougue.weapon_data.di.WeaponModule

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
        lateinit var settingsModule: SettingsModule
        lateinit var spellsModule: SpellsModule
        lateinit var equipmentModule: EquipmentModule
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModule()
        dataSourceModule = DataSourceModule(
            this,
            appModule.dispatcherProvider
        )

        userInfoModule = UserInfoModule(
            this,
            appModule.dispatcherProvider
        )

        authModule = AuthModule(
            appModule.dispatcherProvider,
            dataSourceModule.remoteDataSource.authDao,
            userInfoModule.useCases
        )
        characterModule = CharacterModule(
            appModule.dispatcherProvider,
            dataSourceModule.remoteDataSource.characterDao,
            dataSourceModule.localDataSource.characterDao()
        )
        healthModule = HealthModule(
            appModule.dispatcherProvider,
            dataSourceModule.remoteDataSource.healthDao,
            dataSourceModule.localDataSource.healthDao()
        )
        abilityModule = AbilityModule(
            appModule.dispatcherProvider,
            dataSourceModule.remoteDataSource.abilityDao,
            dataSourceModule.localDataSource.abilityDao()
        )
        skillModule = SkillModule(
            dataSourceModule.remoteDataSource.skillDao,
            dataSourceModule.localDataSource.skillDao()
        )
        statsModule = StatsModule(
            dataSourceModule.remoteDataSource.statsDao,
            dataSourceModule.localDataSource.statsDao()
        )
        moneyModule = MoneyModule(
            appModule.dispatcherProvider,
            dataSourceModule.remoteDataSource.moneyDao,
            dataSourceModule.localDataSource.moneyDao()
        )
        itemModule = ItemModule(
            appModule.dispatcherProvider,
            dataSourceModule.remoteDataSource.itemDao,
            dataSourceModule.localDataSource.itemDao()
        )
        classModule = ClassModule(
            dataSourceModule.remoteDataSource.classDao,
            dataSourceModule.localDataSource.classDao()
        )
        damageTypeModule = DamageTypeModule(
            dataSourceModule.remoteDataSource.damageTypeDao,
            dataSourceModule.localDataSource.damageTypeDao()
        )
        characterSpellModule = CharacterSpellModule(
            appModule.dispatcherProvider,
            dataSourceModule.remoteDataSource.characterSpellDao,
            dataSourceModule.localDataSource.characterSpellDao()
        )
        spellModule = SpellModule(
            dataSourceModule.remoteDataSource.spellDao,
            dataSourceModule.localDataSource.spellDao()
        )
        propertyModule = PropertyModule(
            dataSourceModule.remoteDataSource.propertyDao,
            dataSourceModule.localDataSource.propertyDao()
        )
        weaponModule = WeaponModule(
            appModule.dispatcherProvider,
            dataSourceModule.remoteDataSource.weaponDao,
            dataSourceModule.localDataSource.weaponDao()
        )

        characterSheetModule = CharacterSheetModule(
            appModule.dispatcherProvider,
            dataSourceModule.remoteDataSource.characterSheetDao,
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
        settingsModule = SettingsModule(
            appModule.dispatcherProvider,
            authModule.useCases,
            userInfoModule.useCases
        )
        spellsModule = SpellsModule(
            appModule.dispatcherProvider,
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
    }
}