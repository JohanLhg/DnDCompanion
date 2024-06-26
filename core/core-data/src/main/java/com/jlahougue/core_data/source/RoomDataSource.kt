package com.jlahougue.core_data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jlahougue.ability_data.AbilityLocalDataSource
import com.jlahougue.ability_data.util.AbilityNameTypeConverter
import com.jlahougue.ability_domain.model.Ability
import com.jlahougue.ability_domain.model.AbilityModifierView
import com.jlahougue.ability_domain.model.AbilityView
import com.jlahougue.character_data.CharacterLocalDataSource
import com.jlahougue.character_domain.model.Character
import com.jlahougue.character_domain.model.ProficiencyView
import com.jlahougue.character_spell_data.CharacterSpellLocalDataSource
import com.jlahougue.character_spell_domain.model.CharacterSpell
import com.jlahougue.character_spell_domain.model.CharacterSpellsStatsView
import com.jlahougue.character_spell_domain.model.SpellSlot
import com.jlahougue.character_spell_domain.model.SpellSlotView
import com.jlahougue.character_spell_domain.model.SpellcasterView
import com.jlahougue.class_data.ClassLocalDataSource
import com.jlahougue.class_domain.model.Class
import com.jlahougue.class_domain.model.ClassLevel
import com.jlahougue.class_domain.model.ClassSpellSlot
import com.jlahougue.damage_type_data.DamageTypeLocalDataSource
import com.jlahougue.damage_type_domain.model.DamageType
import com.jlahougue.health_data.HealthLocalDataSource
import com.jlahougue.health_domain.model.DeathSaves
import com.jlahougue.health_domain.model.Health
import com.jlahougue.health_domain.model.HitDiceView
import com.jlahougue.item_data.ItemLocalDataSource
import com.jlahougue.item_domain.model.Item
import com.jlahougue.money_data.MoneyLocalDataSource
import com.jlahougue.money_data.util.CurrencyTypeConverter
import com.jlahougue.money_domain.model.Money
import com.jlahougue.note.data.NoteLocalDataSource
import com.jlahougue.note.domain.model.Note
import com.jlahougue.property_data.PropertyLocalDataSource
import com.jlahougue.property_domain.model.Property
import com.jlahougue.skill_data.SkillLocalDataSource
import com.jlahougue.skill_domain.model.Skill
import com.jlahougue.skill_domain.model.SkillView
import com.jlahougue.spell_data.SpellLocalDataSource
import com.jlahougue.spell_domain.model.Spell
import com.jlahougue.spell_domain.model.SpellClass
import com.jlahougue.spell_domain.model.SpellDamageType
import com.jlahougue.spell_domain.model.SpellSource
import com.jlahougue.stats_data.StatsLocalDataSource
import com.jlahougue.stats_domain.model.Stats
import com.jlahougue.stats_domain.model.StatsView
import com.jlahougue.weapon_data.WeaponLocalDataSource
import com.jlahougue.weapon_domain.model.CharacterWeapon
import com.jlahougue.weapon_domain.model.Weapon
import com.jlahougue.weapon_domain.model.WeaponProperty

@Database(
    entities = [
        Character::class,
        Health::class, DeathSaves::class,
        Ability::class, Skill::class, Stats::class,
        Money::class, Note::class, Item::class,
        Class::class, ClassLevel::class, ClassSpellSlot::class,
        DamageType::class, Property::class,
        Spell::class, SpellClass::class, SpellDamageType::class, SpellSource::class,
        CharacterSpell::class, SpellSlot::class,
        CharacterWeapon::class, Weapon::class, WeaponProperty::class
    ],
    views = [
        HitDiceView::class,
        AbilityModifierView::class, AbilityView::class,
        SkillView::class,
        StatsView::class,
        ProficiencyView::class,
        SpellcasterView::class, CharacterSpellsStatsView::class, SpellSlotView::class
    ],
    version = 24,
    exportSchema = false
)
@TypeConverters(
    value = [AbilityNameTypeConverter::class, CurrencyTypeConverter::class]
)
abstract class RoomDataSource : RoomDatabase() {
    abstract fun characterDao(): CharacterLocalDataSource
    abstract fun healthDao(): HealthLocalDataSource
    abstract fun abilityDao(): AbilityLocalDataSource
    abstract fun skillDao(): SkillLocalDataSource
    abstract fun statsDao(): StatsLocalDataSource
    abstract fun moneyDao(): MoneyLocalDataSource
    abstract fun noteDao(): NoteLocalDataSource
    abstract fun itemDao(): ItemLocalDataSource
    abstract fun classDao(): ClassLocalDataSource
    abstract fun damageTypeDao(): DamageTypeLocalDataSource
    abstract fun propertyDao(): PropertyLocalDataSource
    abstract fun characterSpellDao(): CharacterSpellLocalDataSource
    abstract fun spellDao(): SpellLocalDataSource
    abstract fun weaponDao(): WeaponLocalDataSource

    companion object {
        const val DATABASE_NAME = "dnd_companion_database"
    }
}