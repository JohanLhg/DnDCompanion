package com.jlahougue.dndcompanion.core.data.source.remote

import com.jlahougue.dndcompanion.core.data.source.remote.subsource.Dnd5eDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.subsource.FirebaseDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.subsource.Open5eDataSource
import com.jlahougue.dndcompanion.data_class.data.source.remote.ClassMixedRemoteDataSource
import com.jlahougue.dndcompanion.data_spell.data.source.remote.SpellMixedRemoteDataSource
import com.jlahougue.dndcompanion.data_weapon.data.source.remote.WeaponMixedRemoteDataSource

class MixedRemoteDataSource(
    private val firebaseDataSource: FirebaseDataSource,
    private val dnd5eDataSource: Dnd5eDataSource,
    private val open5eDataSource: Open5eDataSource
): RemoteDataSource {
    override val characterSheetDao by lazy { firebaseDataSource.characterSheetDao }
    override val characterDao by lazy { firebaseDataSource.characterDao }
    override val abilityDao by lazy { firebaseDataSource.abilityDao }
    override val skillDao by lazy { firebaseDataSource.skillDao }
    override val classDao by lazy {
        ClassMixedRemoteDataSource(
            open5eDataSource.classDao,
            dnd5eDataSource.classDao
        )
    }
    override val damageTypeDao by lazy { dnd5eDataSource.damageTypeDao }
    override val spellDao by lazy {
        SpellMixedRemoteDataSource(
            firebaseDataSource.spellDao,
            open5eDataSource.spellDao
        )
    }
    override val propertyDao by lazy { dnd5eDataSource.propertyDao }
    override val weaponDao by lazy {
        WeaponMixedRemoteDataSource(
            firebaseDataSource.weaponDao,
            dnd5eDataSource.weaponDao
        )
    }
}