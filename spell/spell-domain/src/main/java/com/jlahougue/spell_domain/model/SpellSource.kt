package com.jlahougue.spell_domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "spell_source")
data class SpellSource(
    @PrimaryKey
    val title: String,
    val selected: Boolean = true
)
