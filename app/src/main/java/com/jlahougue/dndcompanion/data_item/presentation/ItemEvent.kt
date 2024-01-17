package com.jlahougue.dndcompanion.data_item.presentation

import com.jlahougue.dndcompanion.data_item.domain.model.Item

sealed class ItemEvent {
    data class OnItemClicked(val itemId: Long) : ItemEvent()
    data class OnQuantityChanged(val item: Item, val quantity: Int) : ItemEvent()
}