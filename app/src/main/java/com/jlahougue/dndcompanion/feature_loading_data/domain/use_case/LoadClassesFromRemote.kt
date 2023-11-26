package com.jlahougue.dndcompanion.feature_loading_data.domain.use_case

import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.data.source.remote.subsources.ApiEvent
import com.jlahougue.dndcompanion.core.domain.util.UiText
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_class.domain.repository.IClassRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LoadClassesFromRemote(

    private val dispatcherProvider: DispatcherProvider,
    private val classRepository: IClassRepository
): LoadFromRemote(title = UiText.StringResource(R.string.loading_classes)) {
    override operator fun invoke() {
        CoroutineScope(dispatcherProvider.io).launch {
            classRepository.loadAll(::onApiEvent)

            onApiEvent(ApiEvent.Finish)
        }
    }
}