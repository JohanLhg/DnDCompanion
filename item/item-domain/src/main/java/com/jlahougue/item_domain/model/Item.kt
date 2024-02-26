package com.jlahougue.item_domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.jlahougue.money_domain.model.Currency

@Entity(
    tableName = Item.TABLE_ITEM,
    primaryKeys = [Item.ITEM_CID, Item.ITEM_ID]
)
data class Item(
    @ColumnInfo(name = ITEM_CID)
    val cid: Long = 0,
    @ColumnInfo(name = ITEM_ID)
    var id: Long = 0,
    @ColumnInfo(name = ITEM_QUANTITY)
    val quantity: Int = 1,
    @ColumnInfo(name = ITEM_NAME)
    val name: String = "",
    @ColumnInfo(name = ITEM_DESCRIPTION)
    val description: String = "",
    @ColumnInfo(name = ITEM_COST)
    val cost: Int = 0,
    @ColumnInfo(name = ITEM_COST_CURRENCY)
    val currency: Currency = Currency.COPPER,
    @ColumnInfo(name = ITEM_WEIGHT)
    val weight: Float = 0f
) {
    companion object {
        const val TABLE_ITEM = "item"
        const val ITEM_CID = "cid"
        const val ITEM_ID = "id"
        const val ITEM_QUANTITY = "quantity"
        const val ITEM_NAME = "name"
        const val ITEM_DESCRIPTION = "description"
        const val ITEM_COST = "cost"
        const val ITEM_COST_CURRENCY = "currency"
        const val ITEM_WEIGHT = "weight"
    }

    override fun toString(): String {
        return "$id - $quantity x $name \n$description"
    }
}
