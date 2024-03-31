package com.jlahougue.character_selection_presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlahougue.character_domain.model.Character
import com.jlahougue.character_selection_domain.ICharacterSelectionModule
import com.jlahougue.core_domain.util.LoadImageError
import com.jlahougue.core_domain.util.response.Result
import com.jlahougue.core_presentation.util.UiEvent
import com.jlahougue.core_presentation.util.asUiText
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterSelectionViewModel(
    private val module: ICharacterSelectionModule
) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<UiEvent>(1)
    val uiEvent = _uiEvent.asSharedFlow()

    private val _characterIsSelected = MutableStateFlow(false)
    val characterIsSelected = _characterIsSelected.asStateFlow()

    private val _characters = MutableStateFlow(emptyList<Character>())
    val characters = _characters.asStateFlow()

    private val _characterImages = MutableStateFlow(emptyMap<Long, String>())
    val characterImages = _characterImages.asStateFlow()

    private var imagesLoading: MutableList<Long> = mutableListOf()

    init {
        getCharacterList()
        isCharacterSelected()
    }

    private fun getCharacterList() {
        viewModelScope.launch(module.dispatcherProvider.io) {
            module.characterUseCases.getCharacters().collectLatest { list ->
                _characters.update { list }
            }
        }
    }

    private fun isCharacterSelected() {
        viewModelScope.launch(module.dispatcherProvider.io) {
            module.userInfoUseCases.getCurrentCharacterId().collectLatest { characterId ->
                if (characterId != -1L) _characterIsSelected.update { true }
            }
        }
    }

    fun setCharacter(characterId: Long) {
        module.userInfoUseCases.updateUserInfo(characterId = characterId)
    }

    fun createCharacter() {
        viewModelScope.launch(module.dispatcherProvider.io) {
            setCharacter(module.characterSheetUseCases.createCharacter())
        }
    }

    fun getCharacterImage(characterId: Long) {
        if (imagesLoading.contains(characterId)) return
        imagesLoading += characterId
        module.characterUseCases.loadCharacterImage(
            characterId,
            onComplete = { result ->
                onImageLoadResult(characterId, result)
            }
        )
    }

    private fun onImageLoadResult(characterId: Long, result: Result<String, LoadImageError>) {
        when (result) {
            is Result.Failure -> {
                _characterImages.update { it + (characterId to "") }
                if (result.error == LoadImageError.NO_IMAGE) return
                viewModelScope.launch(module.dispatcherProvider.main) {
                    _uiEvent.emit(UiEvent.ShowError(result.error.asUiText()))
                }
            }
            is Result.Success -> {
                _characterImages.update { it + (characterId to result.data) }
            }
        }
    }
}