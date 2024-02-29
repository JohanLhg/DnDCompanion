package com.jlahougue.core_di

interface IDataSourceModule {
    val remoteDataSource: RemoteDataSource
    val localDataSource: LocalDataSource
}