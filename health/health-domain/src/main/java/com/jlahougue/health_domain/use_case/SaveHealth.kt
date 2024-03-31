package com.jlahougue.health_domain.use_case

import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.health_domain.model.Health
import com.jlahougue.health_domain.repository.IHealthRepository
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