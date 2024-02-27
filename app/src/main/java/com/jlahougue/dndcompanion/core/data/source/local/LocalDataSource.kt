package com.jlahougue.dndcompanion.core.data.source.local

import com.jlahougue.ability_data.source.local.AbilityLocalDataSource
import com.jlahougue.character_data.source.local.CharacterLocalDataSource
import com.jlahougue.class_data.source.local.ClassLocalDataSource
import com.jlahougue.dndcompanion.data_character_spell.data.source.local.CharacterSpellLocalDataSource
import com.jlahougue.dndcompanion.data_currency.data.source.local.MoneyLocalDataSource
import com.jlahougue.dndcompanion.data_damage_type.data.source.local.DamageTypeLocalDataSource
import com.jlahougue.dndcompanion.data_health.data.source.local.HealthLocalDataSource
import com.jlahougue.dndcompanion.data_item.data.source.local.ItemLocalDataSource
import com.jlahougue.dndcompanion.data_property.data.source.local.PropertyLocalDataSource
import com.jlahougue.dndcompanion.data_skill.data.source.local.SkillLocalDataSource
import com.jlahougue.dndcompanion.data_spell.data.source.local.SpellLocalDataSource
import com.jlahougue.dndcompanion.data_stats.data.source.local.StatsLocalDataSource
import com.jlahougue.dndcompanion.data_weapon.data.source.local.WeaponLocalDataSource

interface LocalDataSource {
    fun characterDao(): CharacterLocalDataSource
    fun healthDao(): HealthLocalDataSource
    fun abilityDao(): AbilityLocalDataSource
    fun skillDao(): SkillLocalDataSource
    fun statsDao(): StatsLocalDataSource
    fun moneyDao(): MoneyLocalDataSource
    fun itemDao(): ItemLocalDataSource
    fun classDao(): ClassLocalDataSource
    fun damageTypeDao(): DamageTypeLocalDataSource
    fun characterSpellDao(): CharacterSpellLocalDataSource
    fun spellDao(): SpellLocalDataSource
    fun propertyDao(): PropertyLocalDataSource
    fun weaponDao(): WeaponLocalDataSource
}