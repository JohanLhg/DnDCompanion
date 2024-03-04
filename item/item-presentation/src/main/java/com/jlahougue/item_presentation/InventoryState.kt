package com.jlahougue.item_presentation

import com.jlahougue.item_domain.model.Item
import com.jlahougue.item_presentation.dialog.ItemDialogState

data class InventoryState(
    val items: List<Item> = emptyList(),
    val dialog: ItemDialogState = ItemDialogState()
)
