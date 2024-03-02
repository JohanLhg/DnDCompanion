package com.jlahougue.ability_data.util

import androidx.room.TypeConverter
import com.jlahougue.ability_domain.model.AbilityName

class AbilityNameTypeConverter {
    @TypeConverter
    fun toAbilityName(value: String) = AbilityName.fromCode(value)

    @TypeConverter
    fun fromAbilityName(value: AbilityName) = value.code
}