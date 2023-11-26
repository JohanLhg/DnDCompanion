package com.jlahougue.dndcompanion.core.data.source.remote

import com.jlahougue.dndcompanion.data_class.data.source.remote.ClassRemoteDataSource
import com.jlahougue.dndcompanion.data_damage_type.data.source.remote.DamageTypeRemoteDataSource
import com.jlahougue.dndcompanion.data_spell.data.source.remote.SpellRemoteDataSource
import com.jlahougue.dndcompanion.data_weapon.data.source.remote.WeaponRemoteDataSource

interface RemoteDataSource {
    val classDao: ClassRemoteDataSource
    val damageTypeDao: DamageTypeRemoteDataSource
    val spellDao: SpellRemoteDataSource
    val weaponDao: WeaponRemoteDataSource
}