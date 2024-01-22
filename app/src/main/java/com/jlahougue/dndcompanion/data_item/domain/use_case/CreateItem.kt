package com.jlahougue.dndcompanion.data_item.domain.use_case

import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_item.domain.model.Item
import com.jlahougue.dndcompanion.data_item.domain.repository.IItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class CreateItem(
    private val dispatcherProvider: DispatcherProvider,
    private val itemRepository: IItemRepository
) {
    suspend operator fun invoke(characterId: Long): Flow<Item> {
        return withContext(dispatcherProvider.io) {
            itemRepository.create(characterId)
        }
    }
}