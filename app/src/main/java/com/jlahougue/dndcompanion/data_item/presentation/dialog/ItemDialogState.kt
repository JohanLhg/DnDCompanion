package com.jlahougue.dndcompanion.data_item.presentation.dialog

import com.jlahougue.item_domain.model.Item

data class ItemDialogState(
    val isShown: Boolean = false,
    val item: Item? = null
)
