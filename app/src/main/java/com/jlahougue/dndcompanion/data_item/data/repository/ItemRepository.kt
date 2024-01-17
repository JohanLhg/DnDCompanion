package com.jlahougue.dndcompanion.data_item.data.repository

import com.jlahougue.dndcompanion.data_item.data.source.local.ItemLocalDataSource
import com.jlahougue.dndcompanion.data_item.data.source.remote.ItemRemoteDataSource
import com.jlahougue.dndcompanion.data_item.domain.model.Item
import com.jlahougue.dndcompanion.data_item.domain.repository.IItemRepository

class ItemRepository(
    private val remoteDataSource: ItemRemoteDataSource,
    private val localDataSource: ItemLocalDataSource
) : IItemRepository {
    override suspend fun save(item: Item) {
        remoteDataSource.save(item)
        localDataSource.insert(item)
    }

    override suspend fun saveToLocal(item: Item) {
        localDataSource.insert(item)
    }

    override suspend fun saveToLocal(items: List<Item>) {
        localDataSource.insert(items)
    }

    override fun get(characterId: Long, itemId: Long) = localDataSource.get(characterId, itemId)

    override fun getAll(characterId: Long) = localDataSource.getAll(characterId)
}