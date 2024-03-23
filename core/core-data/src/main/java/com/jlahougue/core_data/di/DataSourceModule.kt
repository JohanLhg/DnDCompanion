package com.jlahougue.core_data.di

import android.app.Application
import androidx.room.Room
import com.jlahougue.core_data.source.local.RoomDataSource
import com.jlahougue.core_data.source.remote.MixedRemoteDataSource
import com.jlahougue.core_data_interface.RemoteGenericDataSource
import com.jlahougue.core_data_remote_instance.Dnd5eDataSource
import com.jlahougue.core_data_remote_instance.FirebaseDataSource
import com.jlahougue.core_data_remote_instance.Open5eDataSource
import com.jlahougue.core_di.IDataSourceModule
import com.jlahougue.core_di.LocalDataSource
import com.jlahougue.core_di.RemoteDataSource
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import okhttp3.OkHttpClient

class DataSourceModule(
    app: Application,
    dispatcherProvider: DispatcherProvider
): IDataSourceModule {
    private val firebaseDataSource by lazy { FirebaseDataSource() }
    private val okHttpClient by lazy { OkHttpClient() }
    private val apiDataSource by lazy { RemoteGenericDataSource(okHttpClient) }
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
            apiDataSource,
            dnd5eDataSource,
            open5eDataSource
        )
    }
}