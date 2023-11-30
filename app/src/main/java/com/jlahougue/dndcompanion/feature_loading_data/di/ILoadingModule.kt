package com.jlahougue.dndcompanion.feature_loading_data.di

import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.feature_loading_data.domain.use_case.LoadAll

interface ILoadingModule {
    val dispatcherProvider: DispatcherProvider
    val loadAll: LoadAll
}