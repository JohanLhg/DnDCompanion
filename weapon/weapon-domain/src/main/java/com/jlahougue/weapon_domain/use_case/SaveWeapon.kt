package com.jlahougue.weapon_domain.use_case

import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.weapon_domain.model.CharacterWeapon
import com.jlahougue.weapon_domain.repository.IWeaponRepository
import kotlinx.coroutines.withContext

class SaveWeapon(
    private val dispatcherProvider: DispatcherProvider,
    private val repository: IWeaponRepository
) {
    suspend operator fun invoke(weapon: CharacterWeapon) {
        withContext(dispatcherProvider.io) {
            repository.save(weapon)
        }
    }
}