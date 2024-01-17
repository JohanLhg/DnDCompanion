package com.jlahougue.dndcompanion.data_item.data.source.remote

import com.jlahougue.dndcompanion.data_item.domain.model.Item

interface ItemRemoteDataSource {
    suspend fun save(item: Item)
}