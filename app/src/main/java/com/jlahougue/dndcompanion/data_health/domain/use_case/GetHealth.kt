package com.jlahougue.dndcompanion.data_health.domain.use_case

import com.jlahougue.dndcompanion.data_health.domain.repository.IHealthRepository

class GetHealth(private val repository: IHealthRepository) {
    operator fun invoke(characterId: Long) = repository.getHealth(characterId)
}