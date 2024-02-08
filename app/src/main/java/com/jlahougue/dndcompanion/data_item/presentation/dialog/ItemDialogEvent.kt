package com.jlahougue.dndcompanion.data_item.presentation.dialog

import com.jlahougue.dndcompanion.data_currency.domain.model.Currency
import com.jlahougue.dndcompanion.data_item.domain.model.Item

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