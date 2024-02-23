package com.jlahougue.dndcompanion.feature_character_selection.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlahougue.dndcompanion.data_character.domain.model.Character
import com.jlahougue.dndcompanion.data_character.domain.use_case.LoadImageState
import com.jlahougue.dndcompanion.feature_character_selection.di.ICharacterSelectionModule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterSelectionViewModel(
    private val module: ICharacterSelectionModule
) : ViewModel() {

    private var _characters = MutableStateFlow(emptyList<Character>())
    val characters = _characters.asStateFlow()

    init {
        getCharacterList()
    }

    private fun getCharacterList() {
        viewModelScope.launch(module.dispatcherProvider.io) {
            module.characterUseCases.getCharacters().collectLatest { list ->
                _characters.update { list }
            }
        }
    }

    fun setCharacter(characterId: Long) {
        module.userInfoRepository.updateCharacterId(characterId)
    }

    fun createCharacter() {
        viewModelScope.launch(module.dispatcherProvider.io) {
            setCharacter(module.characterUseCases.createCharacter())
        }
    }

    fun getCharacterImage(characterId: Long): StateFlow<LoadImageState> {
        return module.characterUseCases.loadCharacterImage(characterId)
    }
}