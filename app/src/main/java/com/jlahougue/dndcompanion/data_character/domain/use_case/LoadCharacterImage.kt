package com.jlahougue.dndcompanion.data_character.domain.use_case

import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.domain.util.UiText
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_character.data.source.remote.CharacterImageFirebaseEvent
import com.jlahougue.dndcompanion.data_character.domain.repository.ICharacterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoadCharacterImage(
    private val dispatcherProvider: DispatcherProvider,
    private val characterRepository: ICharacterRepository
) {
    private val _event = MutableSharedFlow<UiText>()
    val event = _event.asSharedFlow()

    operator fun invoke(characterId: Long): StateFlow<LoadImageState> {
        val state = MutableStateFlow(LoadImageState())
        characterRepository.loadImage(
            characterId,
            onEvent = { onEvent(state, it) }
        )
        return state.asStateFlow()
    }

    fun onEvent(
        state: MutableStateFlow<LoadImageState>,
        event: CharacterImageFirebaseEvent
    ) {
        when (event) {
            is CharacterImageFirebaseEvent.Started -> {
                state.update {
                    it.copy(
                        actionState = LoadImageState.ActionState.STARTED
                    )
                }
            }
            is CharacterImageFirebaseEvent.Canceled -> {
                CoroutineScope(dispatcherProvider.io).launch {
                    _event.emit(UiText.StringResource(R.string.error_fetching_character_image))
                    state.update {
                        it.copy(
                            actionState = LoadImageState.ActionState.FINISHED
                        )
                    }
                }
            }
            is CharacterImageFirebaseEvent.Failure -> {
                CoroutineScope(dispatcherProvider.io).launch {
                    _event.emit(event.message)
                    state.update {
                        it.copy(
                            actionState = LoadImageState.ActionState.FINISHED
                        )
                    }
                }
            }
            is CharacterImageFirebaseEvent.Success -> {
                state.update {
                    it.copy(
                        uri = event.uri,
                        actionState = LoadImageState.ActionState.FINISHED
                    )
                }
            }
        }
    }
}