package com.jlahougue.dndcompanion.data_ability.data.source.remote

interface AbilityRemoteDataSource {
    fun save(ability: com.jlahougue.ability_domain.model.Ability)
}