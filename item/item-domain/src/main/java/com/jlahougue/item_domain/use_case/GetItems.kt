package com.jlahougue.item_domain.use_case

import com.jlahougue.item_domain.repository.IItemRepository

class GetItems(private val itemRepository: IItemRepository) {
    operator fun invoke(characterId: Long) = itemRepository.getAll(characterId)
}