package com.jlahougue.dndcompanion.data_weapon.data.source.remote.subsource

import com.jlahougue.dndcompanion.core.data.source.remote.subsource.FirebaseDataSource
import com.jlahougue.weapon_domain.model.CharacterWeapon

class WeaponFirebaseDataSource(
    private val firebaseDataSource: FirebaseDataSource
) {
    fun save(characterWeapon: CharacterWeapon) {
        firebaseDataSource.updateCharacterSheet(
            characterWeapon.cid,
            mapOf("weapons.${characterWeapon.name}" to characterWeapon)
        )
    }
}