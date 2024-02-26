package com.jlahougue.money_domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Money.TABLE_MONEY)
data class Money(
    @PrimaryKey
    @ColumnInfo(name = MONEY_CID)
    var cid: Long = 0,
    @ColumnInfo(name = MONEY_COPPER_PIECES)
    var copperPieces: Int = 0,
    @ColumnInfo(name = MONEY_OTHER_CURRENCIES)
    var otherCurrencies: String = ""
) {
    companion object {
        const val TABLE_MONEY = "money"
        const val MONEY_CID = "cid"
        const val MONEY_COPPER_PIECES = "copper_pieces"
        const val MONEY_OTHER_CURRENCIES = "other_currencies"
    }

    override fun toString(): String {
        return """
            $cid
            $copperPieces CP
            $otherCurrencies
        """.trimIndent()
    }
}