package com.jlahougue.item_domain.repository

import com.jlahougue.item_domain.model.Item
import kotlinx.coroutines.flow.Flow

interface IItemRepository {
    suspend fun create(characterId: Long): Long
    suspend fun save(item: Item)
    suspend fun saveToLocal(item: Item)
    suspend fun saveToLocal(items: List<Item>)
    suspend fun delete(characterId: Long)
    suspend fun delete(item: Item)
    fun get(characterId: Long, itemId: Long): Flow<Item>
    fun getAll(characterId: Long): Flow<List<Item>>
}