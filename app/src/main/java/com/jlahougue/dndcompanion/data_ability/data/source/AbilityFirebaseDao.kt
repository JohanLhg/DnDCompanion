package com.jlahougue.dndcompanion.data_ability.data.source

import com.jlahougue.dndcompanion.core.data.source.remote.subsources.FirebaseDataSource
import com.jlahougue.dndcompanion.data_ability.domain.model.Ability

class AbilityFirebaseDao(
    private val firebaseDataSource: FirebaseDataSource
) {
    fun save(ability: Ability) {
        firebaseDataSource.updateCharacterSheet(
            ability.cid,
            mapOf("abilities.${ability.name}" to ability)
        )
    }
}