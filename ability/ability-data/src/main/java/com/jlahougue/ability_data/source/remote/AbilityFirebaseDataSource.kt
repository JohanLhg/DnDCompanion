package com.jlahougue.ability_data.source.remote

import com.jlahougue.ability_domain.model.Ability
import com.jlahougue.core_data_remote_instance.FirebaseDataSource

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