package com.jlahougue.health_domain.use_case

import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.health_domain.model.DeathSaves
import com.jlahougue.health_domain.repository.IHealthRepository
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