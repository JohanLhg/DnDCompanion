package com.jlahougue.character_selection_presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlahougue.character_domain.model.Character
import com.jlahougue.character_selection_domain.ICharacterSelectionModule
import com.jlahougue.core_domain.util.LoadImageState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterSelectionViewModel(
    private val module: ICharacterSelectionModule
) : ViewModel() {

    private val _characterIsSelected = MutableStateFlow(false)
    val characterIsSelected = _characterIsSelected.asStateFlow()

    private val _characters = MutableStateFlow(emptyList<Character>())
    val characters = _characters.asStateFlow()

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

    fun getCharacterImage(characterId: Long): StateFlow<LoadImageState> {
        return module.characterUseCases.loadCharacterImage(characterId)
    }
}