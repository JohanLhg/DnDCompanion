package com.jlahougue.weapon_domain.use_case

import com.jlahougue.ability_domain.model.AbilityName
import com.jlahougue.weapon_domain.repository.IWeaponRepository
import kotlinx.coroutines.flow.map

class GetWeapons(private val repository: IWeaponRepository) {
    operator fun invoke(
        characterId: Long,
        search: String? = null,
        filter: AbilityName? = null
    ) = repository.get(characterId).map {
        it.filter { weapon ->
            weapon.name.contains(search ?: "", ignoreCase = true) &&
            (filter == null || weapon.test == filter)
        }
    }
}