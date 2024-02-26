package com.jlahougue.dndcompanion.data_item.data.repository

import com.jlahougue.dndcompanion.data_item.data.source.local.ItemLocalDataSource
import com.jlahougue.dndcompanion.data_item.data.source.remote.ItemRemoteDataSource
import com.jlahougue.item_domain.model.Item
import com.jlahougue.item_domain.repository.IItemRepository

class ItemRepository(
    private val remoteDataSource: ItemRemoteDataSource,
    private val localDataSource: ItemLocalDataSource
) : IItemRepository {
    override suspend fun create(characterId: Long): Long {
        val id = localDataSource.getLastId(characterId) + 1
        save(Item(cid = characterId, id = id))
        return id
    }

    override suspend fun save(item: Item) {
        localDataSource.insert(item)
        remoteDataSource.save(item)
    }

    override suspend fun saveToLocal(item: Item) {
        localDataSource.insert(item)
    }

    override suspend fun saveToLocal(items: List<Item>) {
        localDataSource.insert(items)
    }

    override suspend fun delete(characterId: Long) {
        localDataSource.delete(characterId)
    }

    override suspend fun delete(item: Item) {
        localDataSource.delete(item)
        remoteDataSource.delete(item)
    }

    override fun get(characterId: Long, itemId: Long) = localDataSource.get(characterId, itemId)

    override fun getAll(characterId: Long) = localDataSource.getAll(characterId)
}