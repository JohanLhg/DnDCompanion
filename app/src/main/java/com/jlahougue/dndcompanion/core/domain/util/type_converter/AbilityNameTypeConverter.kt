package com.jlahougue.dndcompanion.core.domain.util.type_converter

import androidx.room.TypeConverter
import com.jlahougue.dndcompanion.core.domain.model.enums.AbilityName

class AbilityNameTypeConverter {
    @TypeConverter
    fun toAbilityName(value: String) = AbilityName.fromCode(value)

    @TypeConverter
    fun fromAbilityName(value: AbilityName) = value.code
}