package com.jlahougue.dndcompanion.data_item.data.di

import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.core.data.source.local.LocalDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource
import com.jlahougue.dndcompanion.data_item.data.repository.ItemRepository
import com.jlahougue.item_domain.di.IItemModule
import com.jlahougue.item_domain.repository.IItemRepository
import com.jlahougue.item_domain.use_case.CreateItem
import com.jlahougue.item_domain.use_case.DeleteItem
import com.jlahougue.item_domain.use_case.GetItem
import com.jlahougue.item_domain.use_case.GetItems
import com.jlahougue.item_domain.use_case.ItemUseCases
import com.jlahougue.item_domain.use_case.SaveItem

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
            createItem = CreateItem(
                dispatcherProvider,
                repository
            ),
            saveItem = SaveItem(
                dispatcherProvider,
                repository
            ),
            deleteItem = DeleteItem(
                dispatcherProvider,
                repository
            )
        )
    }
}