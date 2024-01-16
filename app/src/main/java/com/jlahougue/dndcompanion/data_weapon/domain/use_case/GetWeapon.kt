package com.jlahougue.dndcompanion.data_weapon.domain.use_case

import com.jlahougue.dndcompanion.data_weapon.domain.repository.IWeaponRepository

class GetWeapon(private val repository: IWeaponRepository) {
    operator fun invoke(characterId: Long, weaponName: String)
            = repository.get(characterId, weaponName)
}