package com.jlahougue.dndcompanion.data_item.data.repository

import com.jlahougue.dndcompanion.data_item.data.source.local.ItemLocalDataSource
import com.jlahougue.dndcompanion.data_item.data.source.remote.ItemRemoteDataSource
import com.jlahougue.dndcompanion.data_item.domain.model.Item
import com.jlahougue.dndcompanion.data_item.domain.repository.IItemRepository
import kotlinx.coroutines.flow.Flow

class ItemRepository(
    private val remoteDataSource: ItemRemoteDataSource,
    private val localDataSource: ItemLocalDataSource
) : IItemRepository {
    override suspend fun create(characterId: Long): Flow<Item> {
        val id = save(Item(cid = characterId))
        return get(characterId, id)
    }

    override suspend fun save(item: Item): Long {
        val id = localDataSource.insert(item)
        if (item.id == 0L) item.id = id
        remoteDataSource.save(item)
        return id
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