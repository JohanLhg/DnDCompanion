package com.jlahougue.dndcompanion.core.data.source.remote

import com.jlahougue.dndcompanion.data_ability.data.source.remote.AbilityRemoteDataSource
import com.jlahougue.dndcompanion.data_character.data.source.remote.CharacterRemoteDataSource
import com.jlahougue.dndcompanion.data_class.data.source.remote.ClassRemoteDataSource
import com.jlahougue.dndcompanion.data_damage_type.data.source.remote.DamageTypeRemoteDataSource
import com.jlahougue.dndcompanion.data_property.data.source.remote.PropertyDnd5eDataSource
import com.jlahougue.dndcompanion.data_skill.data.source.remote.SkillRemoteDataSource
import com.jlahougue.dndcompanion.data_spell.data.source.remote.SpellRemoteDataSource
import com.jlahougue.dndcompanion.data_weapon.data.source.remote.WeaponRemoteDataSource

interface RemoteDataSource {
    val characterDao: CharacterRemoteDataSource
    val abilityDao: AbilityRemoteDataSource
    val skillDao: SkillRemoteDataSource
    val classDao: ClassRemoteDataSource
    val damageTypeDao: DamageTypeRemoteDataSource
    val spellDao: SpellRemoteDataSource
    val propertyDao: PropertyDnd5eDataSource
    val weaponDao: WeaponRemoteDataSource
}