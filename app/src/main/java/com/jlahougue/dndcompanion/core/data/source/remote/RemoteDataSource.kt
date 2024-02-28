package com.jlahougue.dndcompanion.core.data.source.remote

import com.jlahougue.ability_data.source.remote.AbilityRemoteDataSource
import com.jlahougue.authentication_data.source.AuthRemoteDataSource
import com.jlahougue.character_data.source.remote.CharacterRemoteDataSource
import com.jlahougue.character_sheet_data.source.remote.CharacterSheetRemoteDataSource
import com.jlahougue.character_spell_data.source.remote.CharacterSpellRemoteDataSource
import com.jlahougue.class_data.source.remote.ClassRemoteDataSource
import com.jlahougue.damage_type_data.source.remote.DamageTypeRemoteDataSource
import com.jlahougue.health_data.source.remote.HealthRemoteDataSource
import com.jlahougue.item_data.source.remote.ItemRemoteDataSource
import com.jlahougue.money_data.source.remote.MoneyRemoteDataSource
import com.jlahougue.property_data.source.remote.PropertyDnd5eDataSource
import com.jlahougue.skill_data.source.remote.SkillRemoteDataSource
import com.jlahougue.spell_data.source.remote.SpellRemoteDataSource
import com.jlahougue.stats_data.source.remote.StatsRemoteDataSource
import com.jlahougue.weapon_data.source.remote.WeaponRemoteDataSource

interface RemoteDataSource {
    val authDao: AuthRemoteDataSource
    val characterSheetDao: CharacterSheetRemoteDataSource
    val characterDao: CharacterRemoteDataSource
    val healthDao: HealthRemoteDataSource
    val abilityDao: AbilityRemoteDataSource
    val skillDao: SkillRemoteDataSource
    val statsDao: StatsRemoteDataSource
    val moneyDao: MoneyRemoteDataSource
    val itemDao: ItemRemoteDataSource
    val classDao: ClassRemoteDataSource
    val damageTypeDao: DamageTypeRemoteDataSource
    val characterSpellDao: CharacterSpellRemoteDataSource
    val spellDao: SpellRemoteDataSource
    val propertyDao: PropertyDnd5eDataSource
    val weaponDao: WeaponRemoteDataSource
}