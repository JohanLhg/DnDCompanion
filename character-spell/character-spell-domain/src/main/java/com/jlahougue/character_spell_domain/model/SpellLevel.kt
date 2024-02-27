package com.jlahougue.character_spell_domain.model


data class SpellLevel(
    val spellSlot: SpellSlotView,
    var spells: List<SpellInfo> = listOf()
) {
    fun filterSpells(
        search: String = "",
        clazz: String = ""
    ) {
        if (search.isEmpty() && clazz.isEmpty()) return
        spells = spells.filter { spell ->
            (search.isEmpty() || spell.name.contains(search, true)) &&
                    (clazz.isEmpty() || spell.classes.any { it.name == clazz })
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