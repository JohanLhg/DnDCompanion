package com.jlahougue.dndcompanion.data_item.domain.use_case

data class ItemUseCases(
    val getItem: GetItem,
    val getItems: GetItems,
    val createItem: CreateItem,
    val saveItem: SaveItem
)
