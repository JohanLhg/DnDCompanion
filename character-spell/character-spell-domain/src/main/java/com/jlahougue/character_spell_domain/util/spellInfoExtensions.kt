package com.jlahougue.character_spell_domain.util

import com.jlahougue.character_spell_domain.model.SpellInfo

fun List<SpellInfo>.filterSpells(
    search: String = "",
    clazz: String = ""
): List<SpellInfo> {
    if (search.isEmpty() && clazz.isEmpty()) return this
    return this.filter { spell ->
        (search.isEmpty() || spell.name.contains(search, true)) &&
                (clazz.isEmpty() || spell.classes.any { it.name == clazz })
    }
}