package com.jlahougue.ability_data.source.remote

import com.jlahougue.ability_domain.model.Ability
import com.jlahougue.core_data_interface.RemoteUserDataSource

class AbilityFirebaseDataSource(
    private val dataSource: RemoteUserDataSource
): AbilityRemoteDataSource {
    override fun save(ability: Ability) {
        dataSource.updateDocument(
            dataSource.characterUrl(ability.cid),
            mapOf("abilities.${ability.name.code}" to ability)
        )
    }
}