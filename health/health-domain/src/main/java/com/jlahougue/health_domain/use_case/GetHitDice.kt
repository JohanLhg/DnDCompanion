package com.jlahougue.health_domain.use_case

import com.jlahougue.health_domain.repository.IHealthRepository

class GetHitDice(private val repository: IHealthRepository) {
    operator fun invoke(characterId: Long) = repository.getHitDice(characterId)
}