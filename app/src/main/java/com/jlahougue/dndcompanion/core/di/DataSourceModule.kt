package com.jlahougue.dndcompanion.core.di

import android.app.Application
import androidx.room.Room
import com.jlahougue.core_data_remote_instance.Dnd5eDataSource
import com.jlahougue.core_data_remote_instance.FirebaseDataSource
import com.jlahougue.core_data_remote_instance.Open5eDataSource
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.core.data.source.local.LocalDataSource
import com.jlahougue.dndcompanion.core.data.source.local.RoomDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.MixedRemoteDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource
import okhttp3.OkHttpClient

class DataSourceModule(
    app: Application,
    dispatcherProvider: DispatcherProvider
): IDataSourceModule {
    private val firebaseDataSource by lazy { FirebaseDataSource() }
    private val okHttpClient by lazy { OkHttpClient() }
    private val dnd5eDataSource by lazy {
        Dnd5eDataSource(okHttpClient)
    }
    private val open5eDataSource by lazy {
        Open5eDataSource(okHttpClient)
    }

    override val localDataSource: LocalDataSource by lazy {
        Room.databaseBuilder(
            app,
            RoomDataSource::class.java,
            RoomDataSource.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
    override val remoteDataSource: RemoteDataSource by lazy {
        MixedRemoteDataSource(
            dispatcherProvider,
            firebaseDataSource,
            dnd5eDataSource,
            open5eDataSource
        )
    }
}