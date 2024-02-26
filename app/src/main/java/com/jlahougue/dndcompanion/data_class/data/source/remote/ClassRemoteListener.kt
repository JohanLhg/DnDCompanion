package com.jlahougue.dndcompanion.data_class.data.source.remote

import com.jlahougue.class_domain.model.Class
import com.jlahougue.class_domain.model.ClassLevel
import com.jlahougue.class_domain.model.ClassSpellSlot

interface ClassRemoteListener {
    suspend fun save(clazz: Class): Boolean
    suspend fun saveLevel(classLevel: ClassLevel): Boolean
    suspend fun saveSpellSlots(classSpellSlots: List<ClassSpellSlot>)
    suspend fun loadClassLevels(className: String)
}