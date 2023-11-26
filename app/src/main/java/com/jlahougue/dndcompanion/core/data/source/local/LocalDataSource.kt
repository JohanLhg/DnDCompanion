package com.jlahougue.dndcompanion.core.data.source.local

import com.jlahougue.dndcompanion.data_class.data.source.local.ClassLevelLocalDataSource
import com.jlahougue.dndcompanion.data_class.data.source.local.ClassLocalDataSource
import com.jlahougue.dndcompanion.data_class.data.source.local.ClassSpellSlotLocalDataSource
import com.jlahougue.dndcompanion.data_damage_type.data.source.local.DamageTypeLocalDataSource
import com.jlahougue.dndcompanion.data_spell.data.source.local.SpellClassLocalDataSource
import com.jlahougue.dndcompanion.data_spell.data.source.local.SpellDamageTypeLocalDataSource
import com.jlahougue.dndcompanion.data_spell.data.source.local.SpellLocalDataSource

interface LocalDataSource {
    fun classDao(): ClassLocalDataSource
    fun classLevelDao(): ClassLevelLocalDataSource
    fun classSpellSlotDao(): ClassSpellSlotLocalDataSource
    fun damageTypeDao(): DamageTypeLocalDataSource
    fun spellDao(): SpellLocalDataSource
    fun spellClassDao(): SpellClassLocalDataSource
    fun spellDamageTypeDao(): SpellDamageTypeLocalDataSource
}