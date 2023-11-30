package com.jlahougue.dndcompanion.feature_loading_data.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.domain.util.UiText
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.feature_loading_data.domain.use_case.LoadAll
import com.jlahougue.dndcompanion.feature_loading_data.presentation.util.LoadingUiEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlin.math.min

class LoadingViewModel(
    val dispatcherProvider: DispatcherProvider,
    val loadAll: LoadAll
) : ViewModel() {

    private val _state = mutableStateOf(LoadingState(title  = UiText.StringResource(R.string.loading)))
    val state: State<LoadingState> = _state

    private val _event = MutableSharedFlow<LoadingUiEvent>()
    val event: SharedFlow<LoadingUiEvent> = _event.asSharedFlow()

    fun onEvent(event: LoadingEvent) {
        when (event) {
            is LoadingEvent.StartLoading -> {
                viewModelScope.launch(dispatcherProvider.io) {
                    loadAll.state.collect {
                        val currentProgress = it.progress.toFloat()
                        val maxProgress = it.progressMax.toFloat()
                        val progress = if (maxProgress == 0f) 0f
                        else min((currentProgress / maxProgress), 1f)
                        _state.value = LoadingState(
                            title = it.title,
                            progress = progress
                        )

                        if (it.hasFinished()) {
                            _event.emit(LoadingUiEvent.NavigateToNextScreen)
                        }
                    }
                }
                loadAll()
            }
        }
    }
}