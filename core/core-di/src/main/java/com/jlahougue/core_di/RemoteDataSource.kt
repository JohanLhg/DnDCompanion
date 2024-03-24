package com.jlahougue.core_di

import com.jlahougue.authentication_data.source.AuthRemoteDataSource
import com.jlahougue.stats_data.source.remote.StatsRemoteDataSource
import com.jlahougue.weapon_data.source.remote.WeaponRemoteDataSource

interface RemoteDataSource {
    val authDao: AuthRemoteDataSource
    val statsDao: StatsRemoteDataSource
    val weaponDao: WeaponRemoteDataSource
}