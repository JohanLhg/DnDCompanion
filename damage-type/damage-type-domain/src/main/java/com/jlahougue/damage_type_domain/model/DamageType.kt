package com.jlahougue.damage_type_domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = DamageType.TABLE_DAMAGE_TYPE)
data class DamageType(
    @PrimaryKey
    @ColumnInfo(name = DAMAGE_TYPE_NAME)
    var name: String = "",
    @ColumnInfo(name = DAMAGE_TYPE_DESCRIPTION)
    var description: String = ""
) {
    companion object {
        const val TABLE_DAMAGE_TYPE = "damage_type"
        const val DAMAGE_TYPE_NAME = "damage_type_name"
        const val DAMAGE_TYPE_DESCRIPTION = "description"
    }
}