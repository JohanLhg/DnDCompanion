package com.jlahougue.dndcompanion.feature_equipment.presentation

import com.jlahougue.dndcompanion.data_currency.presentation.MoneyEvent
import com.jlahougue.dndcompanion.data_currency.presentation.dialog.MoneyDialogEvent
import com.jlahougue.dndcompanion.data_item.presentation.ItemEvent
import com.jlahougue.dndcompanion.data_item.presentation.dialog.ItemDialogEvent
import com.jlahougue.dndcompanion.data_weapon.presentation.WeaponEvent
import com.jlahougue.dndcompanion.data_weapon.presentation.dialog.WeaponDialogEvent
import com.jlahougue.dndcompanion.data_weapon.presentation.list_dialog.WeaponListDialogEvent

sealed class EquipmentEvent {
    data class OnWeaponEvent(val event: WeaponEvent) : EquipmentEvent()
    data class OnWeaponListDialogEvent(val event: WeaponListDialogEvent) : EquipmentEvent()
    data class OnWeaponDialogEvent(val event: WeaponDialogEvent) : EquipmentEvent()
    data class OnMoneyEvent(val event: MoneyEvent) : EquipmentEvent()
    data class OnMoneyDialogEvent(val event: MoneyDialogEvent) : EquipmentEvent()
    data class OnItemEvent(val event: ItemEvent) : EquipmentEvent()
    data class OnItemDialogEvent(val event: ItemDialogEvent) : EquipmentEvent()
}