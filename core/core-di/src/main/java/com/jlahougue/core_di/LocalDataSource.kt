package com.jlahougue.core_di

import com.jlahougue.ability_data.AbilityLocalDataSource
import com.jlahougue.character_data.CharacterLocalDataSource
import com.jlahougue.character_spell_data.CharacterSpellLocalDataSource
import com.jlahougue.class_data.ClassLocalDataSource
import com.jlahougue.damage_type_data.DamageTypeLocalDataSource
import com.jlahougue.health_data.HealthLocalDataSource
import com.jlahougue.item_data.ItemLocalDataSource
import com.jlahougue.money_data.MoneyLocalDataSource
import com.jlahougue.property_data.PropertyLocalDataSource
import com.jlahougue.skill_data.SkillLocalDataSource
import com.jlahougue.spell_data.SpellLocalDataSource
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