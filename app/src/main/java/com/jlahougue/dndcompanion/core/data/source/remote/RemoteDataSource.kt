package com.jlahougue.dndcompanion.core.data.source.remote

import com.jlahougue.ability_data.source.remote.AbilityRemoteDataSource
import com.jlahougue.authentication_data.source.AuthRemoteDataSource
import com.jlahougue.character_data.source.remote.CharacterRemoteDataSource
import com.jlahougue.character_sheet_data.source.remote.CharacterSheetRemoteDataSource
import com.jlahougue.class_data.source.remote.ClassRemoteDataSource
import com.jlahougue.dndcompanion.data_character_spell.data.source.remote.CharacterSpellRemoteDataSource
import com.jlahougue.dndcompanion.data_currency.data.source.remote.MoneyRemoteDataSource
import com.jlahougue.dndcompanion.data_damage_type.data.source.remote.DamageTypeRemoteDataSource
import com.jlahougue.dndcompanion.data_health.data.source.remote.HealthRemoteDataSource
import com.jlahougue.dndcompanion.data_item.data.source.remote.ItemRemoteDataSource
import com.jlahougue.dndcompanion.data_property.data.source.remote.PropertyDnd5eDataSource
import com.jlahougue.dndcompanion.data_skill.data.source.remote.SkillRemoteDataSource
import com.jlahougue.dndcompanion.data_stats.data.source.remote.StatsRemoteDataSource
import com.jlahougue.dndcompanion.data_weapon.data.source.remote.WeaponRemoteDataSource
import com.jlahougue.spell_data.source.remote.SpellRemoteDataSource

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