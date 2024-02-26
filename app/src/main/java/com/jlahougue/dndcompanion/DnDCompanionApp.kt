package com.jlahougue.dndcompanion

import android.app.Application
import com.jlahougue.dndcompanion.core.di.AppModule
import com.jlahougue.dndcompanion.core.di.DataSourceModule
import com.jlahougue.dndcompanion.core.di.IAppModule
import com.jlahougue.dndcompanion.data_ability.di.AbilityModule
import com.jlahougue.dndcompanion.data_authentication.di.AuthModule
import com.jlahougue.dndcompanion.data_character.di.CharacterModule
import com.jlahougue.dndcompanion.data_character_sheet.di.CharacterSheetModule
import com.jlahougue.dndcompanion.data_character_spell.di.CharacterSpellModule
import com.jlahougue.dndcompanion.data_class.di.ClassModule
import com.jlahougue.dndcompanion.data_currency.data.di.MoneyModule
import com.jlahougue.dndcompanion.data_damage_type.data.di.DamageTypeModule
import com.jlahougue.dndcompanion.data_health.data.di.HealthModule
import com.jlahougue.dndcompanion.data_item.data.di.ItemModule
import com.jlahougue.dndcompanion.data_property.data.di.PropertyModule
import com.jlahougue.dndcompanion.data_skill.data.di.SkillModule
import com.jlahougue.dndcompanion.data_spell.data.di.SpellModule
import com.jlahougue.dndcompanion.data_stats.data.di.StatsModule
import com.jlahougue.dndcompanion.data_user_info.data.di.UserInfoModule
import com.jlahougue.dndcompanion.data_weapon.data.di.WeaponModule
import com.jlahougue.dndcompanion.feature_authentication.di.AuthenticationModule
import com.jlahougue.dndcompanion.feature_character_selection.di.CharacterSelectionModule
import com.jlahougue.dndcompanion.feature_combat.di.CombatModule
import com.jlahougue.dndcompanion.feature_equipment.di.EquipmentModule
import com.jlahougue.dndcompanion.feature_loading_data.di.LoadingModule
import com.jlahougue.dndcompanion.feature_spells.di.SpellsModule

class DnDCompanionApp: Application() {

    companion object {
        lateinit var appModule: IAppModule
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
            dataSourceModule.remoteDataSource,
            userInfoModule.useCases
        )
        characterModule = CharacterModule(
            appModule.dispatcherProvider,
            dataSourceModule.remoteDataSource,
            dataSourceModule.localDataSource
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

        characterSheetModule = CharacterSheetModule(
            appModule.dispatcherProvider,
            dataSourceModule.remoteDataSource,
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