package com.jlahougue.dndcompanion

import android.app.Application
import com.jlahougue.dndcompanion.core.di.AppModule
import com.jlahougue.dndcompanion.core.di.DataSourceModule
import com.jlahougue.dndcompanion.core.di.IAppModule
import com.jlahougue.dndcompanion.core.di.IDataSourceModule
import com.jlahougue.dndcompanion.data_ability.di.AbilityModule
import com.jlahougue.dndcompanion.data_ability.di.IAbilityModule
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
import com.jlahougue.dndcompanion.data_damage_type.di.DamageTypeModule
import com.jlahougue.dndcompanion.data_damage_type.di.IDamageTypeModule
import com.jlahougue.dndcompanion.data_health.di.HealthModule
import com.jlahougue.dndcompanion.data_health.di.IHealthModule
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
            userInfoModule.userInfoRepository
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
            dataSourceModule.remoteDataSource,
            dataSourceModule.localDataSource
        )

        characterSheetModule = CharacterSheetModule(
            dataSourceModule.remoteDataSource
        )

        authenticationModule = AuthenticationModule(
            appModule.dispatcherProvider,
            authModule.authUseCases
        )
        loadingModule = LoadingModule(
            appModule.dispatcherProvider,
            characterSheetModule.characterSheetRepository,
            characterModule.characterRepository,
            abilityModule.abilityRepository,
            skillModule.skillRepository,
            classModule.classRepository,
            statsModule.statsRepository,
            healthModule.healthRepository,
            damageTypeModule.damageTypeRepository,
            characterSpellModule.characterSpellRepository,
            spellModule.spellRepository,
            propertyModule.propertyRepository,
            weaponModule.weaponRepository
        )
        characterSelectionModule = CharacterSelectionModule(
            appModule.dispatcherProvider,
            characterModule.characterRepository,
            userInfoModule.userInfoRepository,
            abilityModule.abilityRepository,
            skillModule.skillRepository,
            characterModule.characterUseCases
        )
        combatModule = CombatModule(
            appModule.dispatcherProvider,
            userInfoModule.getCurrentCharacterId,
            abilityModule.abilityUseCases,
            statsModule.statsUseCases,
            healthModule.healthUseCases,
            characterSpellModule.spellUseCases
        )
        spellsModule = SpellsModule(
            appModule.dispatcherProvider,
            userInfoModule.getCurrentCharacterId,
            characterSpellModule.spellUseCases,
            classModule.classUseCases,
            characterModule.characterUseCases
        )
    }
}