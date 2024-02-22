package com.jlahougue.dndcompanion.data_health.di

import com.jlahougue.dndcompanion.data_health.domain.repository.IHealthRepository
import com.jlahougue.dndcompanion.data_health.domain.use_case.HealthUseCases

interface IHealthModule {
    val repository: IHealthRepository
    val useCases: HealthUseCases
}