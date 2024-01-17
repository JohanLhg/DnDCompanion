package com.jlahougue.dndcompanion.data_item.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = Item.TABLE_ITEM,
    primaryKeys = [Item.ITEM_CID, Item.ITEM_ID]
)
data class Item(
    @ColumnInfo(name = ITEM_CID)
    val cid: Long,
    @ColumnInfo(name = ITEM_ID)
    val id: Long,
    @ColumnInfo(name = ITEM_QUANTITY)
    val quantity: Int,
    @ColumnInfo(name = ITEM_NAME)
    val name: String,
    @ColumnInfo(name = ITEM_DESCRIPTION)
    val description: String,
    @ColumnInfo(name = ITEM_COST)
    val cost: Int, // In copper pieces
    @ColumnInfo(name = ITEM_WEIGHT)
    val weight: Int
) {
    companion object {
        const val TABLE_ITEM = "item"
        const val ITEM_CID = "cid"
        const val ITEM_ID = "id"
        const val ITEM_QUANTITY = "quantity"
        const val ITEM_NAME = "name"
        const val ITEM_DESCRIPTION = "description"
        const val ITEM_COST = "cost"
        const val ITEM_WEIGHT = "weight"
    }
}
