package com.jlahougue.dndcompanion.data_item.domain.repository

import com.jlahougue.dndcompanion.data_item.domain.model.Item
import kotlinx.coroutines.flow.Flow

interface IItemRepository {
    suspend fun create(characterId: Long): Flow<Item>
    suspend fun save(item: Item): Long
    suspend fun saveToLocal(item: Item)
    suspend fun saveToLocal(items: List<Item>)
    fun get(characterId: Long, itemId: Long): Flow<Item>
    fun getAll(characterId: Long): Flow<List<Item>>
}