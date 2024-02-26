package com.jlahougue.dndcompanion.feature_equipment.di

import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.item_domain.use_case.ItemUseCases
import com.jlahougue.money_domain.use_case.MoneyUseCases
import com.jlahougue.user_info_domain.use_case.UserInfoUseCases
import com.jlahougue.weapon_domain.use_case.WeaponUseCases

interface IEquipmentModule {
    val dispatcherProvider: DispatcherProvider
    val userInfoUseCases: UserInfoUseCases
    val weaponUseCases: WeaponUseCases
    val moneyUseCases: MoneyUseCases
    val itemUseCases: ItemUseCases
}