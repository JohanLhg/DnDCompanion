package com.jlahougue.core_data.source.remote

import com.jlahougue.authentication_data.source.AuthFirebaseDataSource
import com.jlahougue.core_data_remote_instance.Dnd5eDataSource
import com.jlahougue.core_data_remote_instance.FirebaseDataSource
import com.jlahougue.core_di.RemoteDataSource
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.stats_data.source.remote.StatsFirebaseDataSource
import com.jlahougue.weapon_data.source.remote.WeaponMixedRemoteDataSource
import com.jlahougue.weapon_data.source.remote.subsource.WeaponDnd5eDataSource
import com.jlahougue.weapon_data.source.remote.subsource.WeaponFirebaseDataSource

class MixedRemoteDataSource(
    private val dispatcherProvider: DispatcherProvider,
    private val firebaseDataSource: FirebaseDataSource,
    private val dnd5eDataSource: Dnd5eDataSource
) : RemoteDataSource {
    override val authDao by lazy { AuthFirebaseDataSource(firebaseDataSource.auth) }
    override val statsDao by lazy { StatsFirebaseDataSource(firebaseDataSource) }
    override val weaponDao by lazy {
        WeaponMixedRemoteDataSource(
            WeaponFirebaseDataSource(firebaseDataSource),
            WeaponDnd5eDataSource(
                dispatcherProvider,
                dnd5eDataSource
            )
        )
    }
}