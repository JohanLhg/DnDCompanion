package com.jlahougue.item_domain.use_case

data class ItemUseCases(
    val getItem: GetItem,
    val getItems: GetItems,
    val createItem: CreateItem,
    val saveItem: SaveItem,
    val deleteItem: DeleteItem
)
