package com.jlahougue.dndcompanion.feature_equipment.di

import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_currency.domain.use_case.MoneyUseCases
import com.jlahougue.dndcompanion.data_item.domain.use_case.ItemUseCases
import com.jlahougue.dndcompanion.data_user_info.domain.use_case.UserInfoUseCases
import com.jlahougue.dndcompanion.data_weapon.domain.use_case.WeaponUseCases

class EquipmentModule(
    override val dispatcherProvider: DispatcherProvider,
    override val userInfoUseCases: UserInfoUseCases,
    override val weaponUseCases: WeaponUseCases,
    override val moneyUseCases: MoneyUseCases,
    override val itemUseCases: ItemUseCases
): IEquipmentModule