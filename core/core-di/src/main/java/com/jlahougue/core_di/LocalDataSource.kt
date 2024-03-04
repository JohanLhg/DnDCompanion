package com.jlahougue.core_di

import com.jlahougue.ability_data.source.local.AbilityLocalDataSource
import com.jlahougue.character_data.source.local.CharacterLocalDataSource
import com.jlahougue.character_spell_data.source.local.CharacterSpellLocalDataSource
import com.jlahougue.class_data.source.local.ClassLocalDataSource
import com.jlahougue.damage_type_data.source.local.DamageTypeLocalDataSource
import com.jlahougue.health_data.source.local.HealthLocalDataSource
import com.jlahougue.item_data.source.local.ItemLocalDataSource
import com.jlahougue.money_data.source.local.MoneyLocalDataSource
import com.jlahougue.property_data.source.local.PropertyLocalDataSource
import com.jlahougue.skill_data.source.local.SkillLocalDataSource
import com.jlahougue.spell_data.source.local.SpellLocalDataSource
import com.jlahougue.stats_data.source.local.StatsLocalDataSource
import com.jlahougue.weapon_data.source.local.WeaponLocalDataSource

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