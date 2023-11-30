package com.jlahougue.dndcompanion.core.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jlahougue.dndcompanion.data_ability.data.source.local.AbilityLocalDataSource
import com.jlahougue.dndcompanion.data_ability.domain.model.Ability
import com.jlahougue.dndcompanion.data_ability.domain.util.AbilityNameTypeConverter
import com.jlahougue.dndcompanion.data_character.data.source.local.CharacterLocalDataSource
import com.jlahougue.dndcompanion.data_character.domain.model.Character
import com.jlahougue.dndcompanion.data_class.data.source.local.ClassLocalDataSource
import com.jlahougue.dndcompanion.data_class.domain.model.Class
import com.jlahougue.dndcompanion.data_class.domain.model.ClassLevel
import com.jlahougue.dndcompanion.data_class.domain.model.ClassSpellSlot
import com.jlahougue.dndcompanion.data_damage_type.data.source.local.DamageTypeLocalDataSource
import com.jlahougue.dndcompanion.data_damage_type.domain.model.DamageType
import com.jlahougue.dndcompanion.data_property.data.source.local.PropertyLocalDataSource
import com.jlahougue.dndcompanion.data_property.domain.model.Property
import com.jlahougue.dndcompanion.data_skill.data.source.local.SkillLocalDataSource
import com.jlahougue.dndcompanion.data_skill.domain.model.Skill
import com.jlahougue.dndcompanion.data_spell.data.source.local.SpellLocalDataSource
import com.jlahougue.dndcompanion.data_spell.domain.model.CharacterSpell
import com.jlahougue.dndcompanion.data_spell.domain.model.Spell
import com.jlahougue.dndcompanion.data_spell.domain.model.SpellClass
import com.jlahougue.dndcompanion.data_spell.domain.model.SpellDamageType
import com.jlahougue.dndcompanion.data_weapon.data.source.local.WeaponLocalDataSource
import com.jlahougue.dndcompanion.data_weapon.domain.model.CharacterWeapon
import com.jlahougue.dndcompanion.data_weapon.domain.model.Weapon
import com.jlahougue.dndcompanion.data_weapon.domain.model.WeaponProperty

@Database(
    entities = [
        Character::class,
        Ability::class, Skill::class,
        Class::class, ClassLevel::class, ClassSpellSlot::class,
        DamageType::class, Property::class,
        CharacterSpell::class, Spell::class, SpellClass::class, SpellDamageType::class,
        CharacterWeapon::class, Weapon::class, WeaponProperty::class
    ],
    version = 4
)
@TypeConverters(
    value = [AbilityNameTypeConverter::class]
)
abstract class RoomDataSource : RoomDatabase(), LocalDataSource {
    abstract override fun characterDao(): CharacterLocalDataSource
    abstract override fun abilityDao(): AbilityLocalDataSource
    abstract override fun skillDao(): SkillLocalDataSource
    abstract override fun classDao(): ClassLocalDataSource
    abstract override fun damageTypeDao(): DamageTypeLocalDataSource
    abstract override fun propertyDao(): PropertyLocalDataSource
    abstract override fun spellDao(): SpellLocalDataSource
    abstract override fun weaponDao(): WeaponLocalDataSource

    companion object {
        const val DATABASE_NAME = "dnd_companion_database"
    }
}