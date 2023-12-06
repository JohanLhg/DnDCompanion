package com.jlahougue.dndcompanion.feature_character_selection.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlahougue.dndcompanion.data_character.domain.model.Character
import com.jlahougue.dndcompanion.data_character.domain.use_case.LoadImageState
import com.jlahougue.dndcompanion.feature_character_selection.di.ICharacterSelectionModule
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterSelectionViewModel(
    private val module: ICharacterSelectionModule
) : ViewModel() {

    var characters by mutableStateOf(emptyList<Character>())
        private set

    fun setCharacter(characterId: Long) {
        module.userInfoRepository.updateCharacterId(characterId)
    }

    fun getCharacterList() {
        viewModelScope.launch {
            module.characterRepository.get().collect { list ->
                characters = list
            }
        }
    }

    fun getCharacterImage(characterId: Long): StateFlow<LoadImageState> {
        return module.loadCharacterImage(characterId)
    }
}