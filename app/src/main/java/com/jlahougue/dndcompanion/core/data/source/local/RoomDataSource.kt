package com.jlahougue.dndcompanion.core.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jlahougue.ability_data.source.local.AbilityLocalDataSource
import com.jlahougue.ability_data.util.AbilityNameTypeConverter
import com.jlahougue.ability_domain.model.Ability
import com.jlahougue.ability_domain.model.AbilityModifierView
import com.jlahougue.ability_domain.model.AbilityView
import com.jlahougue.character_data.source.local.CharacterLocalDataSource
import com.jlahougue.character_domain.model.Character
import com.jlahougue.character_domain.model.ProficiencyView
import com.jlahougue.character_spell_data.source.local.CharacterSpellLocalDataSource
import com.jlahougue.character_spell_domain.model.CharacterSpell
import com.jlahougue.character_spell_domain.model.CharacterSpellsStatsView
import com.jlahougue.character_spell_domain.model.SpellSlot
import com.jlahougue.character_spell_domain.model.SpellSlotView
import com.jlahougue.character_spell_domain.model.SpellcasterView
import com.jlahougue.class_data.source.local.ClassLocalDataSource
import com.jlahougue.class_domain.model.Class
import com.jlahougue.class_domain.model.ClassLevel
import com.jlahougue.class_domain.model.ClassSpellSlot
import com.jlahougue.damage_type_data.source.local.DamageTypeLocalDataSource
import com.jlahougue.damage_type_domain.model.DamageType
import com.jlahougue.health_data.source.local.HealthLocalDataSource
import com.jlahougue.health_domain.model.DeathSaves
import com.jlahougue.health_domain.model.Health
import com.jlahougue.item_data.source.local.ItemLocalDataSource
import com.jlahougue.item_domain.model.Item
import com.jlahougue.money_data.source.local.MoneyLocalDataSource
import com.jlahougue.money_data.util.CurrencyTypeConverter
import com.jlahougue.money_domain.model.Money
import com.jlahougue.property_data.source.local.PropertyLocalDataSource
import com.jlahougue.property_domain.model.Property
import com.jlahougue.skill_data.source.local.SkillLocalDataSource
import com.jlahougue.skill_domain.model.Skill
import com.jlahougue.spell_data.source.local.SpellLocalDataSource
import com.jlahougue.spell_domain.model.Spell
import com.jlahougue.spell_domain.model.SpellClass
import com.jlahougue.spell_domain.model.SpellDamageType
import com.jlahougue.stats_data.source.local.StatsLocalDataSource
import com.jlahougue.stats_domain.model.Stats
import com.jlahougue.stats_domain.model.StatsView
import com.jlahougue.weapon_data.source.local.WeaponLocalDataSource
import com.jlahougue.weapon_domain.model.CharacterWeapon
import com.jlahougue.weapon_domain.model.Weapon
import com.jlahougue.weapon_domain.model.WeaponProperty

@Database(
    entities = [
        Character::class,
        Health::class, DeathSaves::class,
        Ability::class, Skill::class, Stats::class,
        Money::class, Item::class,
        Class::class, ClassLevel::class, ClassSpellSlot::class,
        DamageType::class, Property::class,
        Spell::class, SpellClass::class, SpellDamageType::class,
        CharacterSpell::class, SpellSlot::class,
        CharacterWeapon::class, Weapon::class, WeaponProperty::class
    ],
    views = [
        AbilityModifierView::class, AbilityView::class,
        StatsView::class,
        ProficiencyView::class,
        SpellcasterView::class, CharacterSpellsStatsView::class, SpellSlotView::class
    ],
    version = 17
)
@TypeConverters(
    value = [AbilityNameTypeConverter::class, CurrencyTypeConverter::class]
)
abstract class RoomDataSource : RoomDatabase(), LocalDataSource {
    abstract override fun characterDao(): CharacterLocalDataSource
    abstract override fun healthDao(): HealthLocalDataSource
    abstract override fun abilityDao(): AbilityLocalDataSource
    abstract override fun skillDao(): SkillLocalDataSource
    abstract override fun statsDao(): StatsLocalDataSource
    abstract override fun moneyDao(): MoneyLocalDataSource
    abstract override fun itemDao(): ItemLocalDataSource
    abstract override fun classDao(): ClassLocalDataSource
    abstract override fun damageTypeDao(): DamageTypeLocalDataSource
    abstract override fun propertyDao(): PropertyLocalDataSource
    abstract override fun characterSpellDao(): CharacterSpellLocalDataSource
    abstract override fun spellDao(): SpellLocalDataSource
    abstract override fun weaponDao(): WeaponLocalDataSource

    companion object {
        const val DATABASE_NAME = "dnd_companion_database"
    }
}