package com.jlahougue.dndcompanion.data_class.domain.repository

import com.jlahougue.dndcompanion.core.data.source.remote.subsource.ApiEvent
import com.jlahougue.dndcompanion.data_class.domain.model.Class
import com.jlahougue.dndcompanion.data_class.domain.model.ClassLevel
import com.jlahougue.dndcompanion.data_class.domain.model.ClassSpellSlot

interface IClassRepository {
    suspend fun save(clazz: Class): Boolean
    suspend fun saveLevel(classLevel: ClassLevel): Boolean
    suspend fun saveSpellSlots(classSpellSlots: List<ClassSpellSlot>)
    suspend fun loadAll(
        onApiEvent: (ApiEvent) -> Unit
    )
}