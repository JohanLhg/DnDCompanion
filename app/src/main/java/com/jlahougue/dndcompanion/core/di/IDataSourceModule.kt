package com.jlahougue.dndcompanion.core.di

import com.jlahougue.dndcompanion.core.data.source.local.LocalDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource

interface IDataSourceModule {
    val remoteDataSource: RemoteDataSource
    val localDataSource: LocalDataSource
}