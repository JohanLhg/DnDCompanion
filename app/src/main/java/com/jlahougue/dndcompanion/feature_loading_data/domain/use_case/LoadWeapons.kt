package com.jlahougue.dndcompanion.feature_loading_data.domain.use_case

import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.domain.util.UiText
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_weapon.domain.repository.IWeaponRepository
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