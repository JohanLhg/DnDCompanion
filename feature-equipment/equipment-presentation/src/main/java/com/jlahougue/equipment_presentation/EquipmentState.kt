package com.jlahougue.equipment_presentation

import com.jlahougue.item_presentation.InventoryState
import com.jlahougue.money_presentation.MoneyState
import com.jlahougue.property_presentation.PropertyDialogState
import com.jlahougue.settings_domain.model.UnitSystem
import com.jlahougue.weapon_domain.model.WeaponStats
import com.jlahougue.weapon_presentation.WeaponState

data class EquipmentState(
    val unitSystem: UnitSystem = UnitSystem.METRIC,
    val weaponStats: WeaponStats = WeaponStats(),
    val weapons: WeaponState = WeaponState(),
    val money: MoneyState = MoneyState(),
    val inventory: InventoryState = InventoryState(),
    val propertyDialog: PropertyDialogState = PropertyDialogState()
)