package com.jlahougue.class_domain.repository

import com.jlahougue.class_domain.model.Class
import com.jlahougue.class_domain.model.ClassLevel
import com.jlahougue.class_domain.model.ClassSpellSlot
import com.jlahougue.core_domain.util.ApiEvent

interface IClassRepository {
    suspend fun save(clazz: Class): Boolean
    suspend fun saveLevel(classLevel: ClassLevel): Boolean
    suspend fun saveSpellSlots(classSpellSlots: List<ClassSpellSlot>)
    suspend fun loadAll(onApiEvent: (ApiEvent) -> Unit)
    suspend fun get(name: String): Class
    suspend fun get(): List<Class>
    suspend fun getLevels(name: String): Map<ClassLevel, Map<Int, Int>>
    suspend fun getSpellcasterClasses(): List<String>
}