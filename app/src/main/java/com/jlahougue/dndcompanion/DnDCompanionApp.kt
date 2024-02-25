package com.jlahougue.dndcompanion

import android.app.Application
import com.jlahougue.dndcompanion.core.di.AppModule
import com.jlahougue.dndcompanion.core.di.DataSourceModule
import com.jlahougue.dndcompanion.core.di.IAppModule
import com.jlahougue.dndcompanion.core.di.IDataSourceModule
import com.jlahougue.dndcompanion.data_ability.di.AbilityModule
import com.jlahougue.dndcompanion.data_ability.domain.di.IAbilityModule
import com.jlahougue.dndcompanion.data_authentication.di.AuthModule
import com.jlahougue.dndcompanion.data_authentication.di.IAuthModule
import com.jlahougue.dndcompanion.data_character.di.CharacterModule
import com.jlahougue.dndcompanion.data_character.di.ICharacterModule
import com.jlahougue.dndcompanion.data_character_sheet.di.CharacterSheetModule
import com.jlahougue.dndcompanion.data_character_sheet.di.ICharacterSheetModule
import com.jlahougue.dndcompanion.data_character_spell.di.CharacterSpellModule
import com.jlahougue.dndcompanion.data_character_spell.di.ICharacterSpellModule
import com.jlahougue.dndcompanion.data_class.di.ClassModule
import com.jlahougue.dndcompanion.data_class.di.IClassModule
import com.jlahougue.dndcompanion.data_currency.di.IMoneyModule
import com.jlahougue.dndcompanion.data_currency.di.MoneyModule
import com.jlahougue.dndcompanion.data_damage_type.di.DamageTypeModule
import com.jlahougue.dndcompanion.data_damage_type.di.IDamageTypeModule
import com.jlahougue.dndcompanion.data_health.di.HealthModule
import com.jlahougue.dndcompanion.data_health.di.IHealthModule
import com.jlahougue.dndcompanion.data_item.di.IItemModule
import com.jlahougue.dndcompanion.data_item.di.ItemModule
import com.jlahougue.dndcompanion.data_property.di.IPropertyModule
import com.jlahougue.dndcompanion.data_property.di.PropertyModule
import com.jlahougue.dndcompanion.data_skill.di.ISkillModule
import com.jlahougue.dndcompanion.data_skill.di.SkillModule
import com.jlahougue.dndcompanion.data_spell.di.ISpellModule
import com.jlahougue.dndcompanion.data_spell.di.SpellModule
import com.jlahougue.dndcompanion.data_stats.di.IStatsModule
import com.jlahougue.dndcompanion.data_stats.di.StatsModule
import com.jlahougue.dndcompanion.data_user_info.di.IUserInfoModule
import com.jlahougue.dndcompanion.data_user_info.di.UserInfoModule
import com.jlahougue.dndcompanion.data_weapon.di.IWeaponModule
import com.jlahougue.dndcompanion.data_weapon.di.WeaponModule
import com.jlahougue.dndcompanion.feature_authentication.di.AuthenticationModule
import com.jlahougue.dndcompanion.feature_authentication.di.IAuthenticationModule
import com.jlahougue.dndcompanion.feature_character_selection.di.CharacterSelectionModule
import com.jlahougue.dndcompanion.feature_character_selection.di.ICharacterSelectionModule
import com.jlahougue.dndcompanion.feature_combat.di.CombatModule
import com.jlahougue.dndcompanion.feature_combat.di.ICombatModule
import com.jlahougue.dndcompanion.feature_equipment.di.EquipmentModule
import com.jlahougue.dndcompanion.feature_equipment.di.IEquipmentModule
import com.jlahougue.dndcompanion.feature_loading_data.di.ILoadingModule
import com.jlahougue.dndcompanion.feature_loading_data.di.LoadingModule
import com.jlahougue.dndcompanion.feature_spells.di.ISpellsModule
import com.jlahougue.dndcompanion.feature_spells.di.SpellsModule

class DnDCompanionApp: Application() {

    companion object {
        lateinit var appModule: IAppModule
        lateinit var dataSourceModule: IDataSourceModule

        lateinit var userInfoModule: IUserInfoModule

        lateinit var authModule: IAuthModule
        lateinit var characterModule: ICharacterModule
        lateinit var healthModule: IHealthModule
        lateinit var abilityModule: IAbilityModule
        lateinit var skillModule: ISkillModule
        lateinit var statsModule: IStatsModule
        lateinit var moneyModule: IMoneyModule
        lateinit var itemModule: IItemModule
        lateinit var classModule: IClassModule
        lateinit var damageTypeModule: IDamageTypeModule
        lateinit var characterSpellModule: ICharacterSpellModule
        lateinit var spellModule: ISpellModule
        lateinit var propertyModule: IPropertyModule

        lateinit var characterSheetModule: ICharacterSheetModule

        lateinit var weaponModule: IWeaponModule
        lateinit var authenticationModule: IAuthenticationModule
        lateinit var loadingModule: ILoadingModule
        lateinit var characterSelectionModule: ICharacterSelectionModule
        lateinit var combatModule: ICombatModule
        lateinit var spellsModule: ISpellsModule
        lateinit var equipmentModule: IEquipmentModule
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
            dataSourceModule.remoteDataSource,
            userInfoModule.useCases
        )
        healthModule = HealthModule(
            appModule.dispatcherProvider,
            dataSourceModule.remoteDataSource,
            dataSourceModule.localDataSource
        )
        abilityModule = AbilityModule(
            appModule.dispatcherProvider,
            dataSourceModule.remoteDataSource,
            dataSourceModule.localDataSource
        )
        skillModule = SkillModule(
            dataSourceModule.remoteDataSource,
            dataSourceModule.localDataSource
        )
        statsModule = StatsModule(
            dataSourceModule.remoteDataSource,
            dataSourceModule.localDataSource
        )
        moneyModule = MoneyModule(
            appModule.dispatcherProvider,
            dataSourceModule.remoteDataSource,
            dataSourceModule.localDataSource
        )
        itemModule = ItemModule(
            appModule.dispatcherProvider,
            dataSourceModule.remoteDataSource,
            dataSourceModule.localDataSource
        )
        classModule = ClassModule(
            dataSourceModule.remoteDataSource,
            dataSourceModule.localDataSource
        )
        damageTypeModule = DamageTypeModule(
            dataSourceModule.remoteDataSource,
            dataSourceModule.localDataSource
        )
        characterSpellModule = CharacterSpellModule(
            appModule.dispatcherProvider,
            dataSourceModule.remoteDataSource,
            dataSourceModule.localDataSource
        )
        spellModule = SpellModule(
            dataSourceModule.remoteDataSource,
            dataSourceModule.localDataSource
        )
        propertyModule = PropertyModule(
            dataSourceModule.remoteDataSource,
            dataSourceModule.localDataSource
        )
        weaponModule = WeaponModule(
            appModule.dispatcherProvider,
            dataSourceModule.remoteDataSource,
            dataSourceModule.localDataSource
        )

        characterModule = CharacterModule(
            appModule.dispatcherProvider,
            dataSourceModule.remoteDataSource,
            dataSourceModule.localDataSource,
            healthModule.repository,
            abilityModule.repository,
            skillModule.repository,
            statsModule.repository,
            moneyModule.repository,
            itemModule.repository,
            characterSpellModule.repository,
            weaponModule.repository
        )

        characterSheetModule = CharacterSheetModule(
            dataSourceModule.remoteDataSource
        )

        authenticationModule = AuthenticationModule(
            appModule.dispatcherProvider,
            authModule.useCases
        )
        loadingModule = LoadingModule(
            appModule.dispatcherProvider,
            characterSheetModule.repository,
            characterModule.repository,
            characterModule.useCases,
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
            characterModule.useCases
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