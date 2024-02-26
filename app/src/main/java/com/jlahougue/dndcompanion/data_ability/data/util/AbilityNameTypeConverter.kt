package com.jlahougue.dndcompanion.data_ability.data.util

import androidx.room.TypeConverter

class AbilityNameTypeConverter {
    @TypeConverter
    fun toAbilityName(value: String) = com.jlahougue.ability_domain.model.AbilityName.fromCode(value)

    @TypeConverter
    fun fromAbilityName(value: com.jlahougue.ability_domain.model.AbilityName) = value.code
}