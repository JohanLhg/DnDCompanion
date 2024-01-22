package com.jlahougue.dndcompanion.data_item.di

import com.jlahougue.dndcompanion.data_item.domain.repository.IItemRepository
import com.jlahougue.dndcompanion.data_item.domain.use_case.ItemUseCases

interface IItemModule {
    val repository: IItemRepository
    val useCases: ItemUseCases
}