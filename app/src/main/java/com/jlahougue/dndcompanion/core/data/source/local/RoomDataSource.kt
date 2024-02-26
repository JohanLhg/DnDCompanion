package com.jlahougue.dndcompanion.core.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jlahougue.ability_domain.model.Ability
import com.jlahougue.ability_domain.model.AbilityModifierView
import com.jlahougue.ability_domain.model.AbilityView
import com.jlahougue.character_domain.model.Character
import com.jlahougue.character_domain.model.ProficiencyView
import com.jlahougue.class_domain.model.Class
import com.jlahougue.class_domain.model.ClassLevel
import com.jlahougue.class_domain.model.ClassSpellSlot
import com.jlahougue.damage_type_domain.model.DamageType
import com.jlahougue.dndcompanion.data_ability.data.source.local.AbilityLocalDataSource
import com.jlahougue.dndcompanion.data_ability.data.util.AbilityNameTypeConverter
import com.jlahougue.dndcompanion.data_character.data.source.local.CharacterLocalDataSource
import com.jlahougue.dndcompanion.data_character_spell.data.source.local.CharacterSpellLocalDataSource
import com.jlahougue.dndcompanion.data_character_spell.domain.model.CharacterSpell
import com.jlahougue.dndcompanion.data_character_spell.domain.model.CharacterSpellsStatsView
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellSlot
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellSlotView
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellcasterView
import com.jlahougue.dndcompanion.data_class.data.source.local.ClassLocalDataSource
import com.jlahougue.dndcompanion.data_currency.data.source.local.MoneyLocalDataSource
import com.jlahougue.dndcompanion.data_currency.data.util.CurrencyTypeConverter
import com.jlahougue.dndcompanion.data_damage_type.data.source.local.DamageTypeLocalDataSource
import com.jlahougue.dndcompanion.data_health.data.source.local.HealthLocalDataSource
import com.jlahougue.dndcompanion.data_item.data.source.local.ItemLocalDataSource
import com.jlahougue.dndcompanion.data_property.data.source.local.PropertyLocalDataSource
import com.jlahougue.dndcompanion.data_skill.data.source.local.SkillLocalDataSource
import com.jlahougue.dndcompanion.data_spell.data.source.local.SpellLocalDataSource
import com.jlahougue.dndcompanion.data_stats.data.source.local.StatsLocalDataSource
import com.jlahougue.dndcompanion.data_weapon.data.source.local.WeaponLocalDataSource
import com.jlahougue.health_domain.model.DeathSaves
import com.jlahougue.health_domain.model.Health
import com.jlahougue.item_domain.model.Item
import com.jlahougue.money_domain.model.Money
import com.jlahougue.property_domain.model.Property
import com.jlahougue.skill_domain.model.Skill
import com.jlahougue.spell_domain.model.Spell
import com.jlahougue.spell_domain.model.SpellClass
import com.jlahougue.spell_domain.model.SpellDamageType
import com.jlahougue.stats_domain.model.Stats
import com.jlahougue.stats_domain.model.StatsView
import com.jlahougue.weapon_domain.model.CharacterWeapon
import com.jlahougue.weapon_domain.model.Weapon
import com.jlahougue.weapon_domain.model.WeaponProperty

@Database(
    entities = [
        Character::class,
        Health::class, DeathSaves::class,
        com.jlahougue.ability_domain.model.Ability::class, Skill::class, com.jlahougue.stats_domain.model.Stats::class,
        Money::class, Item::class,
        Class::class, ClassLevel::class, ClassSpellSlot::class,
        DamageType::class, Property::class,
        Spell::class, SpellClass::class, SpellDamageType::class,
        CharacterSpell::class, SpellSlot::class,
        CharacterWeapon::class, Weapon::class, WeaponProperty::class
    ],
    views = [
        com.jlahougue.ability_domain.model.AbilityModifierView::class, com.jlahougue.ability_domain.model.AbilityView::class,
        com.jlahougue.stats_domain.model.StatsView::class,
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