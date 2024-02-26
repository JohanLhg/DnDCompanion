package com.jlahougue.item_domain.di

import com.jlahougue.item_domain.repository.IItemRepository
import com.jlahougue.item_domain.use_case.ItemUseCases

interface IItemModule {
    val repository: IItemRepository
    val useCases: ItemUseCases
}