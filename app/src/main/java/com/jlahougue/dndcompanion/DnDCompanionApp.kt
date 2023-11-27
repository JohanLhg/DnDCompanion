package com.jlahougue.dndcompanion

import android.app.Application
import com.jlahougue.dndcompanion.core.di.AppModule
import com.jlahougue.dndcompanion.core.di.DataSourceModule
import com.jlahougue.dndcompanion.core.di.IAppModule
import com.jlahougue.dndcompanion.core.di.IDataSourceModule
import com.jlahougue.dndcompanion.data_ability.di.AbilityModule
import com.jlahougue.dndcompanion.data_ability.di.IAbilityModule
import com.jlahougue.dndcompanion.data_class.di.ClassModule
import com.jlahougue.dndcompanion.data_class.di.IClassModule
import com.jlahougue.dndcompanion.data_damage_type.di.DamageTypeModule
import com.jlahougue.dndcompanion.data_damage_type.di.IDamageTypeModule
import com.jlahougue.dndcompanion.data_property.di.IPropertyModule
import com.jlahougue.dndcompanion.data_property.di.PropertyModule
import com.jlahougue.dndcompanion.data_spell.di.ISpellModule
import com.jlahougue.dndcompanion.data_spell.di.SpellModule
import com.jlahougue.dndcompanion.data_weapon.di.IWeaponModule
import com.jlahougue.dndcompanion.data_weapon.di.WeaponModule
import com.jlahougue.dndcompanion.feature_authentication.di.AuthModule
import com.jlahougue.dndcompanion.feature_authentication.di.IAuthModule
import com.jlahougue.dndcompanion.feature_loading_data.di.ILoadingModule
import com.jlahougue.dndcompanion.feature_loading_data.di.LoadingModule

class DnDCompanionApp: Application() {

    companion object {
        lateinit var appModule: IAppModule
        lateinit var dataSourceModule: IDataSourceModule

        lateinit var abilityModule: IAbilityModule
        lateinit var classModule: IClassModule
        lateinit var damageTypeModule: IDamageTypeModule
        lateinit var spellModule: ISpellModule
        lateinit var propertyModule: IPropertyModule
        lateinit var weaponModule: IWeaponModule

        lateinit var authModule: IAuthModule
        lateinit var loadingModule: ILoadingModule
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModule()
        dataSourceModule = DataSourceModule(this, appModule.dispatcher)

        abilityModule = AbilityModule(
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

        authModule = AuthModule()
        loadingModule = LoadingModule(
            appModule.dispatcher,
            classModule.classRepository,
            damageTypeModule.damageTypeRepository,
            spellModule.spellRepository,
            propertyModule.propertyRepository,
            weaponModule.weaponRepository
        )
    }
}