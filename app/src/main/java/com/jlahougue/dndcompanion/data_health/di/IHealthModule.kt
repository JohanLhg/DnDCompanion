package com.jlahougue.dndcompanion.data_health.di

import com.jlahougue.dndcompanion.data_health.domain.repository.IHealthRepository

interface IHealthModule {
    val healthRepository: IHealthRepository
}