package com.jlahougue.dndcompanion.data_item.di

import com.jlahougue.dndcompanion.core.data.source.local.LocalDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_item.data.repository.ItemRepository
import com.jlahougue.dndcompanion.data_item.domain.repository.IItemRepository
import com.jlahougue.dndcompanion.data_item.domain.use_case.GetItem
import com.jlahougue.dndcompanion.data_item.domain.use_case.GetItems
import com.jlahougue.dndcompanion.data_item.domain.use_case.ItemUseCases
import com.jlahougue.dndcompanion.data_item.domain.use_case.SaveItem

class ItemModule(
    private val dispatcherProvider: DispatcherProvider,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IItemModule {

    override val repository: IItemRepository by lazy {
        ItemRepository(
            remoteDataSource.itemDao,
            localDataSource.itemDao()
        )
    }

    override val useCases: ItemUseCases by lazy {
        ItemUseCases(
            getItem = GetItem(repository),
            getItems = GetItems(repository),
            saveItem = SaveItem(
                dispatcherProvider,
                repository
            )
        )
    }
}