package com.jlahougue.dndcompanion.data_health.domain.use_case

import com.jlahougue.dndcompanion.data_health.domain.repository.IHealthRepository

class GetDeathSaves(private val repository: IHealthRepository) {
    operator fun invoke(characterId: Long) = repository.getDeathSaves(characterId)
}