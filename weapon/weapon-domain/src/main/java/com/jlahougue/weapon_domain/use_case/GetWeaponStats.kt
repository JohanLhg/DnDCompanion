package com.jlahougue.weapon_domain.use_case

import com.jlahougue.weapon_domain.repository.IWeaponRepository

class GetWeaponStats(private val repository: IWeaponRepository) {
    operator fun invoke(characterId: Long) = repository.getStats(characterId)
}