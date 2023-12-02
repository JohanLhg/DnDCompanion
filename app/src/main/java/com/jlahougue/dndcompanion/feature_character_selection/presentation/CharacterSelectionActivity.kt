package com.jlahougue.dndcompanion.feature_character_selection.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jlahougue.dndcompanion.DnDCompanionApp
import com.jlahougue.dndcompanion.core.domain.util.extension.viewModelFactory
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme

class CharacterSelectionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DnDCompanionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = viewModel<CharacterSelectionViewModel>(
                        factory = viewModelFactory {
                            CharacterSelectionViewModel(
                                DnDCompanionApp.characterSelectionModule
                            )
                        }
                    )
                    LaunchedEffect(Unit) {
                        viewModel.getCharacters()
                    }
                    CharacterSelectionScreen(
                        characters = viewModel.characters,
                        getImage = viewModel::getCharacterImage,
                        onCharacterClicked = {},
                        onCharacterAddClicked = {},
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}