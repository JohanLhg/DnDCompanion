package com.jlahougue.dndcompanion.data_character_spell.domain.model

import androidx.room.ColumnInfo
import androidx.room.Junction
import androidx.room.Relation
import com.jlahougue.dndcompanion.data_character_spell.domain.enum.SpellState
import com.jlahougue.dndcompanion.data_class.domain.model.Class
import com.jlahougue.dndcompanion.data_spell.domain.model.Spell
import com.jlahougue.dndcompanion.data_spell.domain.model.SpellClass
import com.jlahougue.dndcompanion.data_spell.domain.model.SpellDamageType

data class SpellInfo(
    @ColumnInfo(name = CharacterSpell.CHARACTER_SPELL_CID)
    var cid: Long = 0,
    @ColumnInfo(name = Spell.SPELL_ID)
    var id: String = "",
    @ColumnInfo(name = Spell.SPELL_NAME)
    var name: String = "",
    @ColumnInfo(name = Spell.SPELL_LEVEL)
    var level: Int = 0,
    @ColumnInfo(name = Spell.SPELL_CASTING_TIME)
    var castingTime: String = "",
    @ColumnInfo(name = Spell.SPELL_RANGE)
    var range: String = "",
    @ColumnInfo(name = Spell.SPELL_COMPONENTS)
    var components: String = "",
    @ColumnInfo(name = Spell.SPELL_MATERIALS)
    var materials: String = "",
    @ColumnInfo(name = Spell.SPELL_RITUAL)
    var ritual: Boolean = false,
    @ColumnInfo(name = Spell.SPELL_CONCENTRATION)
    var concentration: Boolean = false,
    @ColumnInfo(name = Spell.SPELL_DURATION)
    var duration: String = "",
    @ColumnInfo(name = Spell.SPELL_DESCRIPTION)
    var description: String = "",
    @ColumnInfo(name = Spell.SPELL_HIGHER_LEVELS)
    var higherLevels: String = "",

    @ColumnInfo(name = CharacterSpell.CHARACTER_SPELL_STATE)
    var state: SpellState = SpellState.LOCKED,

    @Relation(
        parentColumn = Spell.SPELL_ID,
        entityColumn = Class.CLASS_NAME,
        associateBy = Junction(SpellClass::class)
    )
    var classes: List<Class> = listOf(),

    @Relation(
        parentColumn = Spell.SPELL_ID,
        entityColumn = SpellDamageType.SPELL_DAMAGE_TYPE_SPELL
    )
    var damageTypes: List<SpellDamageType> = listOf()
) {
    fun copy(): SpellInfo {
        return SpellInfo(
            cid = cid,
            id = id,
            name = name,
            level = level,
            castingTime = castingTime,
            range = range,
            components = components,
            materials = materials,
            ritual = ritual,
            concentration = concentration,
            duration = duration,
            description = description,
            higherLevels = higherLevels,
            state = state,
            classes = classes,
            damageTypes = damageTypes
        )
    }

    override fun toString() = "$name ($level)"

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other !is SpellInfo) return false
        return cid == other.cid &&
                id == other.id &&
                name == other.name &&
                level == other.level &&
                castingTime == other.castingTime &&
                range == other.range &&
                components == other.components &&
                materials == other.materials &&
                ritual == other.ritual &&
                concentration == other.concentration &&
                duration == other.duration &&
                description == other.description &&
                higherLevels == other.higherLevels &&
                state == other.state &&
                classes == other.classes &&
                damageTypes == other.damageTypes
    }

    override fun hashCode(): Int {
        var result = cid.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + level
        result = 31 * result + castingTime.hashCode()
        result = 31 * result + range.hashCode()
        result = 31 * result + components.hashCode()
        result = 31 * result + materials.hashCode()
        result = 31 * result + ritual.hashCode()
        result = 31 * result + concentration.hashCode()
        result = 31 * result + duration.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + higherLevels.hashCode()
        result = 31 * result + state.hashCode()
        result = 31 * result + classes.hashCode()
        result = 31 * result + damageTypes.hashCode()
        return result
    }
}