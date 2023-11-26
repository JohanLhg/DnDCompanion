package com.jlahougue.dndcompanion.core.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jlahougue.dndcompanion.data_ability.domain.util.AbilityNameTypeConverter
import com.jlahougue.dndcompanion.data_class.data.source.local.ClassLevelLocalDataSource
import com.jlahougue.dndcompanion.data_class.data.source.local.ClassLocalDataSource
import com.jlahougue.dndcompanion.data_class.data.source.local.ClassSpellSlotLocalDataSource
import com.jlahougue.dndcompanion.data_class.domain.model.Class
import com.jlahougue.dndcompanion.data_class.domain.model.ClassLevel
import com.jlahougue.dndcompanion.data_class.domain.model.ClassSpellSlot
import com.jlahougue.dndcompanion.data_damage_type.data.source.local.DamageTypeLocalDataSource
import com.jlahougue.dndcompanion.data_damage_type.domain.model.DamageType
import com.jlahougue.dndcompanion.data_spell.data.source.local.SpellClassLocalDataSource
import com.jlahougue.dndcompanion.data_spell.data.source.local.SpellDamageTypeLocalDataSource
import com.jlahougue.dndcompanion.data_spell.data.source.local.SpellLocalDataSource
import com.jlahougue.dndcompanion.data_spell.domain.model.Spell
import com.jlahougue.dndcompanion.data_spell.domain.model.SpellClass
import com.jlahougue.dndcompanion.data_spell.domain.model.SpellDamageType
import com.jlahougue.dndcompanion.data_weapon.data.source.local.PropertyDao
import com.jlahougue.dndcompanion.data_weapon.data.source.local.WeaponDao
import com.jlahougue.dndcompanion.data_weapon.data.source.local.WeaponPropertyDao
import com.jlahougue.dndcompanion.data_weapon.domain.model.Property
import com.jlahougue.dndcompanion.data_weapon.domain.model.Weapon
import com.jlahougue.dndcompanion.data_weapon.domain.model.WeaponProperty

@Database(
    entities = [Class::class, ClassLevel::class, ClassSpellSlot::class,
        DamageType::class, Property::class,
        Spell::class, SpellClass::class, SpellDamageType::class,
        Weapon::class, WeaponProperty::class],
    version = 1
)
@TypeConverters(
    value = [AbilityNameTypeConverter::class]
)
abstract class RoomDataSource : RoomDatabase(), LocalDataSource {
    abstract override fun classDao(): ClassLocalDataSource
    abstract override fun classLevelDao(): ClassLevelLocalDataSource
    abstract override fun classSpellSlotDao(): ClassSpellSlotLocalDataSource
    abstract override fun damageTypeDao(): DamageTypeLocalDataSource
    abstract fun propertyDao(): PropertyDao
    abstract override fun spellDao(): SpellLocalDataSource
    abstract override fun spellClassDao(): SpellClassLocalDataSource
    abstract override fun spellDamageTypeDao(): SpellDamageTypeLocalDataSource
    abstract fun weaponDao(): WeaponDao
    abstract fun weaponPropertyDao(): WeaponPropertyDao

    companion object {
        const val DATABASE_NAME = "dnd_companion_database"
    }
}