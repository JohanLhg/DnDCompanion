package com.jlahougue.item_presentation.dialog

import com.jlahougue.item_domain.model.Item
import com.jlahougue.money_domain.model.Currency

sealed class ItemDialogEvent {
    data object OnDismiss : ItemDialogEvent()
    data class OnDelete(val item: Item) : ItemDialogEvent()
    data class OnQuantityChanged(val item: Item, val quantity: Int) : ItemDialogEvent()
    data class OnNameChanged(val item: Item, val name: String) : ItemDialogEvent()
    data class OnCostChanged(val item: Item, val cost: Int) : ItemDialogEvent()
    data class OnCurrencyChanged(val item: Item, val currency: Currency) : ItemDialogEvent()
    data class OnWeightChanged(val item: Item, val weight: Float) : ItemDialogEvent()
    data class OnDescriptionChanged(val item: Item, val description: String) : ItemDialogEvent()
}