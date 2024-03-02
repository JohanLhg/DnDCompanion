package com.jlahougue.dndcompanion.feature_loading_data.domain.use_case

import com.jlahougue.core_domain.util.UiText
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.core_presentation.R
import com.jlahougue.damage_type_domain.repository.IDamageTypeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LoadDamageTypes(
    private val dispatcherProvider: DispatcherProvider,
    private val damageTypeRepository: IDamageTypeRepository
): LoadFromRemote(UiText.StringResource(R.string.loading_damage_types)) {

    override operator fun invoke() {
        super.invoke()
        CoroutineScope(dispatcherProvider.io).launch {
            damageTypeRepository.loadAll(::onApiEvent)
        }
    }
}