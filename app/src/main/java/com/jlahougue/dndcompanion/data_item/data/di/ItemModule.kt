package com.jlahougue.dndcompanion.data_item.data.di

import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_item.data.repository.ItemRepository
import com.jlahougue.dndcompanion.data_item.data.source.local.ItemLocalDataSource
import com.jlahougue.dndcompanion.data_item.data.source.remote.ItemRemoteDataSource
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
    private val remoteDataSource: ItemRemoteDataSource,
    private val localDataSource: ItemLocalDataSource
) : IItemModule {

    override val repository: IItemRepository by lazy {
        ItemRepository(
            remoteDataSource,
            localDataSource
        )
    }

    override val useCases: ItemUseCases by lazy {
        ItemUseCases(
            GetItem(repository),
            GetItems(repository),
            CreateItem(
                dispatcherProvider,
                repository
            ),
            SaveItem(
                dispatcherProvider,
                repository
            ),
            DeleteItem(
                dispatcherProvider,
                repository
            )
        )
    }
}