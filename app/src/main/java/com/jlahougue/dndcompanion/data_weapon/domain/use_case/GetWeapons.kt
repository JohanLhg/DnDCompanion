package com.jlahougue.dndcompanion.data_weapon.domain.use_case

import com.jlahougue.dndcompanion.data_ability.domain.model.AbilityName
import com.jlahougue.dndcompanion.data_weapon.domain.model.WeaponInfo
import com.jlahougue.dndcompanion.data_weapon.domain.repository.IWeaponRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class GetWeapons(private val repository: IWeaponRepository) {
    operator fun invoke(
        characterId: Long,
        search: String? = null,
        filter: AbilityName? = null
    ): Flow<List<WeaponInfo>> {
        var weapons = repository.get(characterId)
        if (search != null) {
            weapons = weapons.onEach {
                it.filter { weapon -> weapon.name.contains(search, ignoreCase = true) }
            }
        }
        if (filter != null) {
            weapons = weapons.onEach {
                it.filter { weapon -> weapon.test == filter }
            }
        }
        return weapons
    }
}