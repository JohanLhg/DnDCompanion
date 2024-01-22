package com.jlahougue.dndcompanion.data_item.domain.use_case

import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_item.domain.repository.IItemRepository
import kotlinx.coroutines.withContext

class CreateItem(
    private val dispatcherProvider: DispatcherProvider,
    private val itemRepository: IItemRepository
) {
    suspend operator fun invoke(characterId: Long): Long {
        return withContext(dispatcherProvider.io) {
            itemRepository.create(characterId)
        }
    }
}