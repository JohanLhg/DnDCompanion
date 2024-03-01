package com.jlahougue.dndcompanion.feature_equipment.presentation

import com.jlahougue.dndcompanion.data_item.presentation.InventoryState
import com.jlahougue.dndcompanion.data_weapon.presentation.WeaponState
import com.jlahougue.weapon_domain.model.WeaponStats

data class EquipmentState(
    val unitSystem: com.jlahougue.settings_domain.model.UnitSystem = com.jlahougue.settings_domain.model.UnitSystem.METRIC,
    val weaponStats: WeaponStats = WeaponStats(),
    val weapons: WeaponState = WeaponState(),
    val money: com.jlahougue.money_presentation.MoneyState = com.jlahougue.money_presentation.MoneyState(),
    val inventory: InventoryState = InventoryState()
)