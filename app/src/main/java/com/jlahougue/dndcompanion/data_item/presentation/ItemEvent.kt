package com.jlahougue.dndcompanion.data_item.presentation

sealed class ItemEvent {
    data class OnItemClicked(val itemId: Long) : ItemEvent()
}