package com.jlahougue.dndcompanion.feature_equipment.di

import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_item.domain.use_case.ItemUseCases
import com.jlahougue.dndcompanion.data_user_info.domain.use_case.UserInfoUseCases
import com.jlahougue.dndcompanion.data_weapon.domain.use_case.WeaponUseCases

interface IEquipmentModule {
    val dispatcherProvider: DispatcherProvider
    val userInfoUseCases: UserInfoUseCases
    val weaponUseCases: WeaponUseCases
    val itemUseCases: ItemUseCases
}