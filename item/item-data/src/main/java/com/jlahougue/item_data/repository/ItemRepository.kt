package com.jlahougue.item_data.repository

import com.jlahougue.item_data.source.local.ItemLocalDataSource
import com.jlahougue.item_data.source.remote.ItemRemoteDataSource
import com.jlahougue.item_domain.model.Item
import com.jlahougue.item_domain.repository.IItemRepository

class ItemRepository(
    private val remote: ItemRemoteDataSource,
    private val local: ItemLocalDataSource
) : IItemRepository {
    override suspend fun create(characterId: Long): Long {
        val id = local.getLastId(characterId) + 1
        save(Item(cid = characterId, id = id))
        return id
    }

    override suspend fun save(item: Item) {
        local.insert(item)
        remote.save(item)
    }

    override suspend fun saveToLocal(item: Item) = local.insert(item)

    override suspend fun saveToLocal(items: List<Item>) = local.insert(items)

    override suspend fun clearLocal() = local.clear()

    override suspend fun delete(characterId: Long) = local.delete(characterId)

    override suspend fun delete(item: Item) {
        local.delete(item)
        remote.delete(item)
    }

    override fun get(characterId: Long, itemId: Long) = local.get(characterId, itemId)

    override fun getAll(characterId: Long) = local.getAll(characterId)
}