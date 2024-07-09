package com.jlahougue.character_sheet_domain.use_case

import com.jlahougue.character_domain.repository.ICharacterRepository
import com.jlahougue.character_spell_domain.repository.ICharacterSpellRepository
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.health_domain.repository.IHealthRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import kotlin.math.max

class TakeLongRest(
    private val dispatcherProvider: DispatcherProvider,
    private val characterRepository: ICharacterRepository,
    private val healthRepository: IHealthRepository,
    private val characterSpellRepository: ICharacterSpellRepository
) {

    suspend operator fun invoke(characterId: Long) {
        withContext(dispatcherProvider.io) {
            val character = characterRepository.get(characterId).first()
            var health = healthRepository.getHealth(characterId).first()
            health = health.copy(
                currentHp = health.maxHp,
                temporaryHp = 0,
                hitDiceUsed = max(health.hitDiceUsed - character.level, 0)
            )
            healthRepository.save(health)
            characterSpellRepository.restoreSlots(characterId)
        }
    }
}