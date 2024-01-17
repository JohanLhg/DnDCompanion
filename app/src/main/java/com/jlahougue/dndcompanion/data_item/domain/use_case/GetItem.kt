package com.jlahougue.dndcompanion.data_item.domain.use_case

import com.jlahougue.dndcompanion.data_item.domain.repository.IItemRepository

class GetItem(private val itemRepository: IItemRepository) {
    operator fun invoke(characterId: Long, itemId: Long) = itemRepository.get(characterId, itemId)
}