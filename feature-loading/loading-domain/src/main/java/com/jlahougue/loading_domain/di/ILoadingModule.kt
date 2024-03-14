package com.jlahougue.loading_domain.di

import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.loading_domain.use_case.LoadAll

interface ILoadingModule {
    val dispatcherProvider: DispatcherProvider
    var loadAll: LoadAll
    fun init()
}