package com.jlahougue.equipment_presentation

import com.jlahougue.item_presentation.ItemEvent
import com.jlahougue.item_presentation.dialog.ItemDialogEvent
import com.jlahougue.money_presentation.MoneyEvent
import com.jlahougue.money_presentation.dialog.MoneyDialogEvent
import com.jlahougue.property_presentation.PropertyDialogEvent
import com.jlahougue.weapon_presentation.WeaponEvent
import com.jlahougue.weapon_presentation.dialog.WeaponDialogEvent
import com.jlahougue.weapon_presentation.list_dialog.WeaponListDialogEvent

sealed class EquipmentEvent {
    data class OnWeaponEvent(val event: WeaponEvent) : EquipmentEvent()
    data class OnWeaponListDialogEvent(val event: WeaponListDialogEvent) : EquipmentEvent()
    data class OnWeaponDialogEvent(val event: WeaponDialogEvent) : EquipmentEvent()
    data class OnMoneyEvent(val event: MoneyEvent) : EquipmentEvent()
    data class OnMoneyDialogEvent(val event: MoneyDialogEvent) : EquipmentEvent()
    data class OnItemEvent(val event: ItemEvent) : EquipmentEvent()
    data class OnItemDialogEvent(val event: ItemDialogEvent) : EquipmentEvent()
    data class OnPropertyDialogEvent(val event: PropertyDialogEvent) : EquipmentEvent()
}