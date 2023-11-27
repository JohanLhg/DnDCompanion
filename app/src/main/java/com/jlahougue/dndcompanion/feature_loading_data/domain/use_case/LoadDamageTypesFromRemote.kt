package com.jlahougue.dndcompanion.feature_loading_data.domain.use_case

import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.domain.util.UiText
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_damage_type.domain.repository.IDamageTypeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LoadDamageTypesFromRemote(
    private val dispatcherProvider: DispatcherProvider,
    private val damageTypeRepository: IDamageTypeRepository
): LoadFromRemote(UiText.StringResource(R.string.loading_damage_types)) {

    override operator fun invoke() {
        CoroutineScope(dispatcherProvider.io).launch {
            damageTypeRepository.loadAll(::onApiEvent)
        }
    }
}