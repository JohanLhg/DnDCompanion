package com.jlahougue.dndcompanion.data_item.domain.repository

import com.jlahougue.dndcompanion.data_item.domain.model.Item
import kotlinx.coroutines.flow.Flow

interface IItemRepository {
    suspend fun save(item: Item)
    suspend fun saveToLocal(item: Item)
    suspend fun saveToLocal(items: List<Item>)
    fun get(characterId: Long, itemId: Long): Flow<Item>
    fun getAll(characterId: Long): Flow<List<Item>>
}