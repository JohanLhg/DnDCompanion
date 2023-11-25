package com.jlahougue.dndcompanion.core.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jlahougue.dndcompanion.core.data.source.local.dao.ClassDao
import com.jlahougue.dndcompanion.core.data.source.local.dao.ClassLevelDao
import com.jlahougue.dndcompanion.core.data.source.local.dao.ClassSpellSlotDao
import com.jlahougue.dndcompanion.core.data.source.local.dao.DamageTypeDao
import com.jlahougue.dndcompanion.core.data.source.local.dao.PropertyDao
import com.jlahougue.dndcompanion.core.data.source.local.dao.SpellClassDao
import com.jlahougue.dndcompanion.core.data.source.local.dao.SpellDamageTypeDao
import com.jlahougue.dndcompanion.core.data.source.local.dao.SpellDao
import com.jlahougue.dndcompanion.core.data.source.local.dao.WeaponDao
import com.jlahougue.dndcompanion.core.data.source.local.dao.WeaponPropertyDao
import com.jlahougue.dndcompanion.core.domain.model.Class
import com.jlahougue.dndcompanion.core.domain.model.ClassLevel
import com.jlahougue.dndcompanion.core.domain.model.ClassSpellSlot
import com.jlahougue.dndcompanion.core.domain.model.DamageType
import com.jlahougue.dndcompanion.core.domain.model.Property
import com.jlahougue.dndcompanion.core.domain.model.Spell
import com.jlahougue.dndcompanion.core.domain.model.SpellClass
import com.jlahougue.dndcompanion.core.domain.model.SpellDamageType
import com.jlahougue.dndcompanion.core.domain.model.Weapon
import com.jlahougue.dndcompanion.core.domain.model.WeaponProperty
import com.jlahougue.dndcompanion.core.domain.util.type_converter.AbilityNameTypeConverter

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
abstract class DnDCompanionDatabase : RoomDatabase() {
    abstract fun classDao(): ClassDao
    abstract fun classLevelDao(): ClassLevelDao
    abstract fun classSpellSlotDao(): ClassSpellSlotDao
    abstract fun damageTypeDao(): DamageTypeDao
    abstract fun propertyDao(): PropertyDao
    abstract fun spellDao(): SpellDao
    abstract fun spellClassDao(): SpellClassDao
    abstract fun spellDamageTypeDao(): SpellDamageTypeDao
    abstract fun weaponDao(): WeaponDao
    abstract fun weaponPropertyDao(): WeaponPropertyDao

    companion object {
        const val DATABASE_NAME = "dnd_companion_database"
    }
}