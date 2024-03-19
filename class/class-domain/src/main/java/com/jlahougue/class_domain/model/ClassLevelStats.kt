package com.jlahougue.class_domain.model

data class ClassLevelStats(
    val level: ClassLevel,
    val spellSlots: Map<Int, Int>
)