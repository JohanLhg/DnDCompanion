package com.jlahougue.loading_domain.use_case

import com.jlahougue.core_domain.util.UiText
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.loading_domain.R
import com.jlahougue.weapon_domain.repository.IWeaponRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LoadWeapons(
    private val dispatcherProvider: DispatcherProvider,
    private val weaponRepository: IWeaponRepository
): LoadFromRemote(UiText.StringResource(R.string.loading_weapons)) {
    override fun invoke() {
        super.invoke()
        CoroutineScope(dispatcherProvider.io).launch {
            weaponRepository.loadAll(::onApiEvent)
        }
    }
}