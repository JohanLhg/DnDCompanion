package com.jlahougue.dndcompanion.data_weapon.domain.use_case

import com.jlahougue.dndcompanion.data_ability.domain.model.AbilityName
import com.jlahougue.dndcompanion.data_weapon.domain.repository.IWeaponRepository
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