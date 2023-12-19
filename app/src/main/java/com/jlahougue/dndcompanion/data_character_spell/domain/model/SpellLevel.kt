package com.jlahougue.dndcompanion.data_character_spell.domain.model


data class SpellLevel(
    val spellSlot: SpellSlotView,
    var spells: List<SpellInfo> = listOf()
) {
    fun filterSpells(search: String = "", classFilter: List<String> = listOf()) {
        if (search.isEmpty() && classFilter.isEmpty()) return
        spells = spells.filter { spell ->
            (search.isEmpty() || spell.name.contains(search, true)) &&
                    (classFilter.isEmpty() || spell.classes.any { clazz -> classFilter.contains(clazz.name) })
        }
    }

    fun copy() = SpellLevel(
        spellSlot.copy(),
        spells.map { it.copy() }
    )

    override fun toString(): String {
        return "\n$spellSlot\n$spells"
    }
}