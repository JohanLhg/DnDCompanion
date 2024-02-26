package com.jlahougue.dndcompanion.data_item.presentation

import com.jlahougue.dndcompanion.data_item.presentation.dialog.ItemDialogState
import com.jlahougue.item_domain.model.Item

data class InventoryState(
    val items: List<Item> = emptyList(),
    val dialog: ItemDialogState = ItemDialogState()
)
