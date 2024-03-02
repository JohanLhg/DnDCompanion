package com.jlahougue.dndcompanion.feature_loading_data.domain.use_case

import com.jlahougue.class_domain.repository.IClassRepository
import com.jlahougue.core_domain.util.UiText
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.core_presentation.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LoadClasses(
    private val dispatcherProvider: DispatcherProvider,
    private val classRepository: IClassRepository
): LoadFromRemote(title = UiText.StringResource(R.string.loading_classes)) {

    override operator fun invoke() {
        super.invoke()
        CoroutineScope(dispatcherProvider.io).launch {
            classRepository.loadAll(::onApiEvent)
        }
    }
}