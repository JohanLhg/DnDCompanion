package com.jlahougue.item_domain.use_case

import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.item_domain.model.Item
import com.jlahougue.item_domain.repository.IItemRepository
import kotlinx.coroutines.withContext

class DeleteItem(
    private val dispatcherProvider: DispatcherProvider,
    private val itemRepository: IItemRepository
) {
    suspend operator fun invoke(item: Item) {
        withContext(dispatcherProvider.io) {
            itemRepository.delete(item)
        }
    }
}