package com.jlahougue.dndcompanion.data_ability.data.source.remote

import com.jlahougue.dndcompanion.core.data.source.remote.subsource.FirebaseDataSource
import com.jlahougue.dndcompanion.data_ability.domain.model.Ability

class AbilityFirebaseDataSource(
    private val firebaseDataSource: FirebaseDataSource
): AbilityRemoteDataSource {
    override fun save(ability: Ability) {
        firebaseDataSource.updateCharacterSheet(
            ability.cid,
            mapOf("abilities.${ability.name.code}" to ability)
        )
    }
}