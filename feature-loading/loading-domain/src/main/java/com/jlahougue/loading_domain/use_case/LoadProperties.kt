package com.jlahougue.loading_domain.use_case

import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.loading_domain.util.LoaderKey
import com.jlahougue.property_domain.repository.IPropertyRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LoadProperties(
    private val dispatcherProvider: DispatcherProvider,
    private val propertyRepository: IPropertyRepository
): LoadFromRemote(LoaderKey.PROPERTIES) {

    override operator fun invoke() {
        super.invoke()
        CoroutineScope(dispatcherProvider.io).launch {
            propertyRepository.loadAll(::onApiEvent)
        }
    }
}