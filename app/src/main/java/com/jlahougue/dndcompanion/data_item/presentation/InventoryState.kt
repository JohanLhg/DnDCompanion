package com.jlahougue.dndcompanion.data_item.presentation

import com.jlahougue.dndcompanion.data_item.domain.model.Item
import com.jlahougue.dndcompanion.data_item.presentation.dialog.ItemDialogState

data class InventoryState(
    val items: List<Item> = emptyList(),
    val dialog: ItemDialogState = ItemDialogState()
)
