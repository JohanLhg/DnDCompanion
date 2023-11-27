package com.jlahougue.dndcompanion.core.data.source.remote

import com.jlahougue.dndcompanion.core.data.source.remote.subsources.Dnd5eDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.subsources.FirebaseDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.subsources.Open5eDataSource
import com.jlahougue.dndcompanion.data_class.data.source.remote.ClassMixedRemoteDataSource
import com.jlahougue.dndcompanion.data_weapon.data.source.remote.WeaponRemoteDataSource

class MixedRemoteDataSource(
    private val firebaseDataSource: FirebaseDataSource,
    private val dnd5eDataSource: Dnd5eDataSource,
    private val open5eDataSource: Open5eDataSource
): RemoteDataSource {
    override val classDao by lazy {
        ClassMixedRemoteDataSource(
            open5eDataSource.classDao,
            dnd5eDataSource.classDao
        )
    }
    override val damageTypeDao by lazy { dnd5eDataSource.damageTypeDao }
    override val spellDao by lazy { open5eDataSource.spellDao }
    override val propertyDao by lazy { dnd5eDataSource.propertyDao }
    override val weaponDao by lazy { dnd5eDataSource.weaponDao }
}