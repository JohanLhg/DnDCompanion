package com.jlahougue.dndcompanion.core.domain.repository

import com.jlahougue.dndcompanion.core.domain.model.Class
import com.jlahougue.dndcompanion.core.domain.model.ClassLevel
import com.jlahougue.dndcompanion.core.domain.model.ClassSpellSlot

interface IClassRepository {
    fun save(clazz: Class): Boolean
    fun saveLevel(classLevel: ClassLevel): Boolean
    fun saveSpellSlots(classSpellSlots: List<ClassSpellSlot>)
}