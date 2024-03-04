package com.jlahougue.equipment_domain

import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.item_domain.use_case.ItemUseCases
import com.jlahougue.money_domain.use_case.MoneyUseCases
import com.jlahougue.user_info_domain.use_case.UserInfoUseCases
import com.jlahougue.weapon_domain.use_case.WeaponUseCases

class EquipmentModule(
    override val dispatcherProvider: DispatcherProvider,
    override val userInfoUseCases: UserInfoUseCases,
    override val weaponUseCases: WeaponUseCases,
    override val moneyUseCases: MoneyUseCases,
    override val itemUseCases: ItemUseCases
): IEquipmentModule