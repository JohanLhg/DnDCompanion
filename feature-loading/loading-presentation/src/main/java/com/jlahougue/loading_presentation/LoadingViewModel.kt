package com.jlahougue.loading_presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlahougue.core_domain.util.UiText
import com.jlahougue.core_presentation.R
import com.jlahougue.loading_domain.di.ILoadingModule
import com.jlahougue.loading_presentation.util.LoadingUiEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.min

class LoadingViewModel(
    private val module: ILoadingModule
) : ViewModel() {

    private val _state = MutableStateFlow(LoadingState(title  = UiText.StringResource(R.string.loading)))
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<LoadingUiEvent>(replay = 1)
    val event: SharedFlow<LoadingUiEvent> = _event.asSharedFlow()

    private var loadingStarted = false

    init { module.init() }

    fun onEvent(event: LoadingEvent) {
        when (event) {
            is LoadingEvent.StartLoading -> {
                if (loadingStarted) return
                loadingStarted = true
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.loadAll.state.collectLatest {
                        val currentProgress = it.progress.toFloat()
                        val maxProgress = it.progressMax.toFloat()
                        val progress = if (maxProgress == 0f) 0f
                        else min((currentProgress / maxProgress), 1f)
                        _state.update { _ ->
                            LoadingState(
                                title = it.title,
                                progress = progress
                            )
                        }

                        if (it.hasFinished()) {
                            _event.emit(LoadingUiEvent.NavigateToNextScreen)
                        }
                    }
                }
                module.loadAll()
            }
            is LoadingEvent.UserAuthenticated -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.loadAll.onUserAuthenticated()
                }
            }
        }
    }
}