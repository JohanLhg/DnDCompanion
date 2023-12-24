package com.jlahougue.dndcompanion.data_health.domain.use_case

import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_health.domain.model.DeathSaves
import com.jlahougue.dndcompanion.data_health.domain.repository.IHealthRepository
import kotlinx.coroutines.withContext

class SaveDeathSaves(
    private val dispatcherProvider: DispatcherProvider,
    private val repository: IHealthRepository
) {
    suspend operator fun invoke(deathSaves: DeathSaves) {
        withContext(dispatcherProvider.io) {
            repository.save(deathSaves)
        }
    }
}