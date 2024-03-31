package com.jlahougue.loading_domain.use_case

import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.damage_type_domain.repository.IDamageTypeRepository
import com.jlahougue.loading_domain.util.LoaderKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LoadDamageTypes(
    private val dispatcherProvider: DispatcherProvider,
    private val damageTypeRepository: IDamageTypeRepository
): LoadFromRemote(LoaderKey.DAMAGE_TYPES) {
    override operator fun invoke() {
        super.invoke()
        CoroutineScope(dispatcherProvider.io).launch {
            damageTypeRepository.loadAll(::onApiEvent)
        }
    }
}