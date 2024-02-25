package com.jlahougue.dndcompanion.data_ability.data.util

import androidx.room.TypeConverter
import com.jlahougue.dndcompanion.data_ability.domain.model.AbilityName

class AbilityNameTypeConverter {
    @TypeConverter
    fun toAbilityName(value: String) = AbilityName.fromCode(value)

    @TypeConverter
    fun fromAbilityName(value: AbilityName) = value.code
}