package com.jlahougue.class_domain.use_case

import com.jlahougue.class_domain.model.ClassLevelStats
import com.jlahougue.class_domain.repository.IClassRepository
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import kotlinx.coroutines.withContext

class GetClassLevels(
    private val dispatcherProvider: DispatcherProvider,
    private val repository: IClassRepository
) {
    suspend operator fun invoke(name: String) = withContext(dispatcherProvider.io) {
        val levelMap = repository.getLevels(name)
        val levels = mutableListOf<ClassLevelStats>()
        levelMap.forEach { (level, spellSlots) ->
            levels += ClassLevelStats(level, spellSlots)
        }
        levels.toList()
    }
}