package com.jlahougue.dndcompanion.data_ability.data.source.remote

import com.jlahougue.dndcompanion.data_ability.domain.model.Ability

interface AbilityRemoteDataSource {
    fun save(ability: Ability)
}