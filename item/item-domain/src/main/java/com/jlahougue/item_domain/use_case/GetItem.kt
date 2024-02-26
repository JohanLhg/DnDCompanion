package com.jlahougue.item_domain.use_case

import com.jlahougue.item_domain.repository.IItemRepository

class GetItem(private val itemRepository: IItemRepository) {
    operator fun invoke(characterId: Long, itemId: Long) = itemRepository.get(characterId, itemId)
}