package com.jlahougue.dndcompanion.data_item.data.source.remote

import com.jlahougue.item_domain.model.Item

interface ItemRemoteDataSource {
    suspend fun save(item: Item)
    suspend fun delete(item: Item)
}