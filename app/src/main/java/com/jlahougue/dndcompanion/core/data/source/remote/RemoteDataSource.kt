package com.jlahougue.dndcompanion.core.data.source.remote

import com.jlahougue.dndcompanion.data_ability.data.source.remote.AbilityRemoteDataSource
import com.jlahougue.dndcompanion.data_authentication.data.source.AuthRemoteDataSource
import com.jlahougue.dndcompanion.data_character.data.source.remote.CharacterRemoteDataSource
import com.jlahougue.dndcompanion.data_character_sheet.data.source.remote.CharacterSheetRemoteDataSource
import com.jlahougue.dndcompanion.data_character_spell.data.source.remote.CharacterSpellRemoteDataSource
import com.jlahougue.dndcompanion.data_class.data.source.remote.ClassRemoteDataSource
import com.jlahougue.dndcompanion.data_damage_type.data.source.remote.DamageTypeRemoteDataSource
import com.jlahougue.dndcompanion.data_health.data.source.remote.HealthRemoteDataSource
import com.jlahougue.dndcompanion.data_property.data.source.remote.PropertyDnd5eDataSource
import com.jlahougue.dndcompanion.data_skill.data.source.remote.SkillRemoteDataSource
import com.jlahougue.dndcompanion.data_spell.data.source.remote.SpellRemoteDataSource
import com.jlahougue.dndcompanion.data_weapon.data.source.remote.WeaponRemoteDataSource

interface RemoteDataSource {
    val authDao: AuthRemoteDataSource
    val characterSheetDao: CharacterSheetRemoteDataSource
    val characterDao: CharacterRemoteDataSource
    val healthDao: HealthRemoteDataSource
    val abilityDao: AbilityRemoteDataSource
    val skillDao: SkillRemoteDataSource
    val classDao: ClassRemoteDataSource
    val damageTypeDao: DamageTypeRemoteDataSource
    val characterSpellDao: CharacterSpellRemoteDataSource
    val spellDao: SpellRemoteDataSource
    val propertyDao: PropertyDnd5eDataSource
    val weaponDao: WeaponRemoteDataSource
}