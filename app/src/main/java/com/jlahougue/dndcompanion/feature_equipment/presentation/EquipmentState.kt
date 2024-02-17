package com.jlahougue.dndcompanion.feature_equipment.presentation

import com.jlahougue.dndcompanion.data_currency.presentation.MoneyState
import com.jlahougue.dndcompanion.data_item.presentation.InventoryState
import com.jlahougue.dndcompanion.data_settings.domain.model.UnitSystem
import com.jlahougue.dndcompanion.data_weapon.domain.model.WeaponStats
import com.jlahougue.dndcompanion.data_weapon.presentation.WeaponState

data class EquipmentState(
    val unitSystem: UnitSystem = UnitSystem.METRIC,
    val weaponStats: WeaponStats = WeaponStats(),
    val weapons: WeaponState = WeaponState(),
    val money: MoneyState = MoneyState(),
    val inventory: InventoryState = InventoryState()
)