package com.jlahougue.dndcompanion.core.data.repository

import com.jlahougue.dndcompanion.core.data.source.local.dao.ClassDao
import com.jlahougue.dndcompanion.core.data.source.local.dao.ClassLevelDao
import com.jlahougue.dndcompanion.core.data.source.local.dao.ClassSpellSlotDao
import com.jlahougue.dndcompanion.core.domain.model.Class
import com.jlahougue.dndcompanion.core.domain.model.ClassLevel
import com.jlahougue.dndcompanion.core.domain.model.ClassSpellSlot
import com.jlahougue.dndcompanion.core.domain.repository.IClassRepository

class ClassRepository(
    private val classDao: ClassDao,
    private val classLevelDao: ClassLevelDao,
    private val classSpellSlotDao: ClassSpellSlotDao
): IClassRepository {
    override fun save(clazz: Class): Boolean {
        return classDao.insert(clazz) != -1L
    }

    override fun saveLevel(classLevel: ClassLevel): Boolean {
        return classLevelDao.insert(classLevel) != -1L
    }

    override fun saveSpellSlots(classSpellSlots: List<ClassSpellSlot>) {
        classSpellSlotDao.insert(classSpellSlots)
    }
}