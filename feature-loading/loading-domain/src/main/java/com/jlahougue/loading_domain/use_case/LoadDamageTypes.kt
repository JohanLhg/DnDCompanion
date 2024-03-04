package com.jlahougue.loading_domain.use_case

import com.jlahougue.core_domain.util.UiText
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.damage_type_domain.repository.IDamageTypeRepository
import com.jlahougue.loading_domain.R
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