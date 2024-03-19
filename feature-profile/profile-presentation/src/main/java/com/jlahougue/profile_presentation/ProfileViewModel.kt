package com.jlahougue.profile_presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlahougue.character_domain.model.Character
import com.jlahougue.class_presentation.detail_dialog.ClassDialogEvent
import com.jlahougue.class_presentation.detail_dialog.ClassDialogState
import com.jlahougue.class_presentation.list_dialog.ClassListDialogEvent
import com.jlahougue.class_presentation.list_dialog.ClassListDialogState
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
    private var uploadImageJob: Job? = null

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
                uploadImageJob?.cancel()
                uploadImageJob = viewModelScope.launch(module.dispatcherProvider.io) {
                    module.characterUseCases.uploadImage(state.value.character.id, event.uri)
                        .collectLatest { imageState ->
                            _state.update {
                                it.copy(image = imageState)
                            }
                        }
                }
            }
            is ProfileEvent.OnNameChanged -> {
                updateCharacter(state.value.character.copy(name = event.name))
            }
            is ProfileEvent.OnRaceChanged -> {
                updateCharacter(state.value.character.copy(race = event.race))
            }
            ProfileEvent.OnClassDetailsOpened -> {
                viewModelScope.launch {
                    _state.update {
                        it.copy(
                            classDialog = ClassDialogState(
                                isShown = true,
                                clazz = module.classUseCases.getClass(state.value.character.clazz),
                                levels = module.classUseCases.getClassLevels(state.value.character.clazz)
                            )
                        )
                    }
                }
            }
            ProfileEvent.OnClassListOpened -> {
                viewModelScope.launch {
                    _state.update {
                        it.copy(
                            classListDialog = it.classListDialog.copy(
                                isShown = true,
                                classes = module.classUseCases.getClasses()
                            )
                        )
                    }
                }
            }
            is ProfileEvent.OnLevelChanged -> {
                updateCharacter(state.value.character.copy(level = event.level))
            }
            ProfileEvent.OnLevelUp -> {
                updateCharacter(state.value.character.copy(level = state.value.character.level + 1))
            }
            is ProfileEvent.OnGenderChanged -> {
                updateCharacter(state.value.character.copy(gender = event.gender))
            }
            is ProfileEvent.OnAgeChanged -> {
                updateCharacter(state.value.character.copy(age = event.age))
            }
            is ProfileEvent.OnHeightChanged -> {
                updateCharacter(state.value.character.copy(height = event.height))
            }
            is ProfileEvent.OnWeightChanged -> {
                updateCharacter(state.value.character.copy(weight = event.weight))
            }
            is ProfileEvent.OnBackgroundChanged -> {
                updateCharacter(state.value.character.copy(background = event.background))
            }
            is ProfileEvent.OnPersonalityChanged -> {
                updateCharacter(state.value.character.copy(personality = event.personality))
            }
            is ProfileEvent.OnIdealsChanged -> {
                updateCharacter(state.value.character.copy(ideals = event.ideals))
            }
            is ProfileEvent.OnBondsChanged -> {
                updateCharacter(state.value.character.copy(bonds = event.bonds))
            }
            is ProfileEvent.OnFlawsChanged -> {
                updateCharacter(state.value.character.copy(flaws = event.flaws))
            }
            is ProfileEvent.OnClassListDialogEvent -> onClassListDialogEvent(event.event)
            is ProfileEvent.OnClassDialogEvent -> onClassDialogEvent(event.event)
        }
    }

    private fun updateCharacter(character: Character) {
        viewModelScope.launch(module.dispatcherProvider.io) {
            module.characterUseCases.updateCharacter(character)
        }
    }

    private fun onClassListDialogEvent(event: ClassListDialogEvent) {
        when (event) {
            ClassListDialogEvent.OnDismiss -> {
                _state.update {
                    it.copy(classListDialog = ClassListDialogState())
                }
            }
            is ClassListDialogEvent.OnClassSelected -> {
                _state.update {
                    it.copy(
                        classListDialog = it.classListDialog.copy(
                            selectedClass = event.clazz
                        )
                    )
                }
            }
            is ClassListDialogEvent.OnClassDetailsOpened -> {
                viewModelScope.launch {
                    _state.update {
                        it.copy(
                            classDialog = ClassDialogState(
                                isShown = true,
                                clazz = event.clazz,
                                levels = module.classUseCases.getClassLevels(event.clazz.name)
                            )
                        )
                    }
                }
            }
            ClassListDialogEvent.OnConfirm -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    val clazz = state.value.classListDialog.selectedClass ?: return@launch
                    module.characterUseCases.updateCharacter(
                        state.value.character.copy(clazz = clazz.name)
                    )
                    onEvent(ProfileEvent.OnClassListDialogEvent(ClassListDialogEvent.OnDismiss))
                }
            }
        }
    }

    private fun onClassDialogEvent(event: ClassDialogEvent) {
        when (event) {
            ClassDialogEvent.OnDismiss -> {
                _state.update {
                    it.copy(classDialog = ClassDialogState())
                }
            }
        }
    }
}