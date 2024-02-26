package com.jlahougue.item_domain.use_case

import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.item_domain.repository.IItemRepository
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