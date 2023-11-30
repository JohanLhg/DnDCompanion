package com.jlahougue.dndcompanion.feature_loading_data.domain.use_case

import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.domain.util.UiText
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_property.domain.repository.IPropertyRepository
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