package com.jlahougue.core_data.di

import android.app.Application
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.jlahougue.core_data.source.RoomDataSource
import com.jlahougue.core_data_interface.RemoteGenericDataSource
import com.jlahougue.core_data_remote_instance.FirebaseDataSource
import okhttp3.OkHttpClient

class DataSourceModule(app: Application) {
    val authDataSource by lazy { FirebaseAuth.getInstance() }
    val firebaseDataSource by lazy { FirebaseDataSource(authDataSource) }
    private val okHttpClient by lazy { OkHttpClient() }
    val apiDataSource by lazy { RemoteGenericDataSource(okHttpClient) }

    val roomDataSource by lazy {
        Room.databaseBuilder(
            app,
            RoomDataSource::class.java,
            RoomDataSource.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}