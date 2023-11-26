package com.jlahougue.dndcompanion.core.di

import android.app.Application
import androidx.room.Room
import com.jlahougue.dndcompanion.core.data.source.local.LocalDataSource
import com.jlahougue.dndcompanion.core.data.source.local.RoomDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.MixedRemoteDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.subsources.Dnd5eDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.subsources.FirebaseDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.subsources.Open5eDataSource
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import okhttp3.OkHttpClient

class DataSourceModule(
    app: Application,
    dispatcherProvider: DispatcherProvider
): IDataSourceModule {
    private val firebaseDataSource by lazy { FirebaseDataSource() }
    private val okHttpClient by lazy { OkHttpClient() }
    private val dnd5eDataSource by lazy {
        Dnd5eDataSource(
            dispatcherProvider,
            okHttpClient
        )
    }
    private val open5eDataSource by lazy {
        Open5eDataSource(
            dispatcherProvider,
            okHttpClient
        )
    }

    override val localDataSource: LocalDataSource by lazy {
        Room.databaseBuilder(
            app,
            RoomDataSource::class.java,
            RoomDataSource.DATABASE_NAME
        ).build()
    }
    override val remoteDataSource: RemoteDataSource by lazy {
        MixedRemoteDataSource(
            firebaseDataSource,
            dnd5eDataSource,
            open5eDataSource
        )
    }
}