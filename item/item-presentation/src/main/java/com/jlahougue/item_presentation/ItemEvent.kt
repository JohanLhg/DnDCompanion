package com.jlahougue.item_presentation

sealed class ItemEvent {
    data class OnItemClicked(val itemId: Long) : ItemEvent()
    data object OnItemCreated : ItemEvent()
}