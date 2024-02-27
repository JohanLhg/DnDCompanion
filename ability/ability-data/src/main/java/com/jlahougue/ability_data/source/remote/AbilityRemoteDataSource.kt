package com.jlahougue.ability_data.source.remote

import com.jlahougue.ability_domain.model.Ability

interface AbilityRemoteDataSource {
    fun save(ability: Ability)
}