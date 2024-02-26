package com.jlahougue.health_domain.di

import com.jlahougue.health_domain.repository.IHealthRepository
import com.jlahougue.health_domain.use_case.HealthUseCases

interface IHealthModule {
    val repository: IHealthRepository
    val useCases: HealthUseCases
}