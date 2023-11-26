package com.jlahougue.dndcompanion.data_property.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Property.TABLE_PROPERTY)
data class Property(
    @PrimaryKey
    @ColumnInfo(name = PROPERTY_NAME)
    var name: String = "",
    @ColumnInfo(name = PROPERTY_DESCRIPTION)
    var description: String = ""
) {
    companion object {
        const val TABLE_PROPERTY = "property"
        const val PROPERTY_NAME = "property_name"
        const val PROPERTY_DESCRIPTION = "description"
    }
}