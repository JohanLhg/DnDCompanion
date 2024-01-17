package com.jlahougue.dndcompanion.data_item.domain.use_case

import com.jlahougue.dndcompanion.data_item.domain.repository.IItemRepository

class GetItems(private val itemRepository: IItemRepository) {
    operator fun invoke(characterId: Long) = itemRepository.getAll(characterId)
}