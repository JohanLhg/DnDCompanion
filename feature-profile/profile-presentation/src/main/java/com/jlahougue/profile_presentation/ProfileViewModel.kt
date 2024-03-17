package com.jlahougue.profile_presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlahougue.class_presentation.ClassDialogState
import com.jlahougue.profile_domain.ProfileModule
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(private val module: ProfileModule) : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()

    private var characterJob: Job? = null
    private var imageJob: Job? = null

    init {
        viewModelScope.launch(module.dispatcherProvider.io) {
            module.userInfoUseCases.getUserInfo().collectLatest { userInfo ->
                if (userInfo.characterId == -1L) return@collectLatest

                characterJob?.cancel()
                characterJob = viewModelScope.launch(module.dispatcherProvider.io) {
                    module.characterUseCases.getCharacter(userInfo.characterId)
                        .collectLatest { character ->
                            _state.value = _state.value.copy(character = character)
                        }
                }

                loadCharacterImage(userInfo.characterId)
            }
        }
    }

    private fun loadCharacterImage(characterId: Long) {
        imageJob?.cancel()
        imageJob = viewModelScope.launch(module.dispatcherProvider.io) {
            module.characterUseCases.loadCharacterImage(characterId)
                .collectLatest { imageState ->
                    _state.value = _state.value.copy(image = imageState)
                }
        }
    }

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.OnImageSelected -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.characterUseCases.uploadImage(state.value.character.id, event.uri)
                        .collectLatest { imageState ->
                            _state.update {
                                it.copy(image = imageState)
                            }
                        }
                }
            }
            is ProfileEvent.OnNameChanged -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.characterUseCases.updateCharacter(
                        state.value.character.copy(name = event.name)
                    )
                }
            }
            is ProfileEvent.OnRaceChanged -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.characterUseCases.updateCharacter(
                        state.value.character.copy(race = event.race)
                    )
                }
            }
            ProfileEvent.OnClassListOpened -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.classUseCases.getClass("Wizard").collectLatest { wizard ->
                        _state.update {
                            it.copy(
                                classDialog = ClassDialogState(
                                    isShown = true,
                                    clazz = wizard
                                )
                            )
                        }
                    }
                }
            }
            is ProfileEvent.OnLevelChanged -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.characterUseCases.updateCharacter(
                        state.value.character.copy(level = event.level)
                    )
                }
            }
            ProfileEvent.OnLevelUp -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.characterUseCases.updateCharacter(
                        state.value.character.copy(level = state.value.character.level + 1)
                    )
                }
            }
            is ProfileEvent.OnGenderChanged -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.characterUseCases.updateCharacter(
                        state.value.character.copy(gender = event.gender)
                    )
                }
            }
            is ProfileEvent.OnAgeChanged -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.characterUseCases.updateCharacter(
                        state.value.character.copy(age = event.age)
                    )
                }
            }
            is ProfileEvent.OnHeightChanged -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.characterUseCases.updateCharacter(
                        state.value.character.copy(height = event.height)
                    )
                }
            }
            is ProfileEvent.OnWeightChanged -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.characterUseCases.updateCharacter(
                        state.value.character.copy(weight = event.weight)
                    )
                }
            }
            is ProfileEvent.OnBackgroundChanged -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.characterUseCases.updateCharacter(
                        state.value.character.copy(background = event.background)
                    )
                }
            }
            is ProfileEvent.OnPersonalityChanged -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.characterUseCases.updateCharacter(
                        state.value.character.copy(personality = event.personality)
                    )
                }
            }
        }
    }
}