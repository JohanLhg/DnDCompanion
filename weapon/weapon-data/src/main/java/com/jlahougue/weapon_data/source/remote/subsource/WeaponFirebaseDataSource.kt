package com.jlahougue.weapon_data.source.remote.subsource

import com.jlahougue.core_data_remote_instance.FirebaseDataSource
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