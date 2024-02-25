package com.jlahougue.dndcompanion.data_health.domain.use_case

import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_health.domain.model.Health
import com.jlahougue.dndcompanion.data_health.domain.repository.IHealthRepository
import kotlinx.coroutines.withContext

class SaveHealth(
    private val dispatcherProvider: DispatcherProvider,
    private val repository: IHealthRepository
) {
    suspend operator fun invoke(health: Health) {
        withContext(dispatcherProvider.io) {
            repository.save(health)
        }
    }
}