package com.jlahougue.dndcompanion.data_ability.data.source.remote

import com.jlahougue.dndcompanion.core.data.source.remote.subsource.FirebaseDataSource

class AbilityFirebaseDataSource(
    private val firebaseDataSource: FirebaseDataSource
): AbilityRemoteDataSource {
    override fun save(ability: com.jlahougue.ability_domain.model.Ability) {
        firebaseDataSource.updateCharacterSheet(
            ability.cid,
            mapOf("abilities.${ability.name.code}" to ability)
        )
    }
}