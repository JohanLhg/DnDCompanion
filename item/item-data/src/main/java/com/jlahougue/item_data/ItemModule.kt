package com.jlahougue.item_data

import com.jlahougue.core_data_interface.RemoteUserDataSource
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.item_domain.di.IItemModule
import com.jlahougue.item_domain.repository.IItemRepository
import com.jlahougue.item_domain.use_case.CreateItem
import com.jlahougue.item_domain.use_case.DeleteItem
import com.jlahougue.item_domain.use_case.GetItem
import com.jlahougue.item_domain.use_case.GetItems
import com.jlahougue.item_domain.use_case.ItemUseCases
import com.jlahougue.item_domain.use_case.SaveItem

class ItemModule(
    dispatcherProvider: DispatcherProvider,
    remoteDataSource: RemoteUserDataSource,
    localDataSource: ItemLocalDataSource
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