package com.jlahougue.spell_data.util

import com.jlahougue.spell_domain.model.Spell
import org.json.JSONObject

fun JSONObject.toSpell(): Spell {
    val id = this.getString("slug")
    val name = this.getString("name").replace("/", " - ")
    val level = this.getInt("level_int")
    val castingTime = this.getString("casting_time")
    val range = this.getString("range")
    val duration = this.getString("duration")
    val ritual = this.getBoolean("can_be_cast_as_ritual")
    val concentration = this.getBoolean("requires_concentration")

    val components = this.getString("components")
    val materials = this.getString("material")

    val desc = this.getString("desc")
    val higherLevel = this.getString("higher_level")

    return Spell(
        id,
        name,
        level,
        castingTime,
        range,
        components,
        materials,
        ritual,
        concentration,
        duration,
        desc,
        higherLevel
    )
}