package com.jlahougue.dndcompanion.feature_loading_data.domain.use_case

import com.jlahougue.core_domain.util.UiText
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.core_presentation.R
import com.jlahougue.property_domain.repository.IPropertyRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LoadProperties(
    private val dispatcherProvider: DispatcherProvider,
    private val propertyRepository: IPropertyRepository
): LoadFromRemote(UiText.StringResource(R.string.loading_weapon_properties)) {

    override operator fun invoke() {
        super.invoke()
        CoroutineScope(dispatcherProvider.io).launch {
            propertyRepository.loadAll(::onApiEvent)
        }
    }
}