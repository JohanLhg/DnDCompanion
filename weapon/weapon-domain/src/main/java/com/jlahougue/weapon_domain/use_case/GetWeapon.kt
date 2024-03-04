package com.jlahougue.weapon_domain.use_case

import com.jlahougue.weapon_domain.repository.IWeaponRepository

class GetWeapon(private val repository: IWeaponRepository) {
    operator fun invoke(characterId: Long, weaponName: String)
            = repository.get(characterId, weaponName)
}