package com.jlahougue.dndcompanion.feature_character_selection.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.data_character.domain.model.Character

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
                    var characters: List<Character> by remember {
                        mutableStateOf(listOf(
                            Character(
                                id = 0,
                                name = "Daeron Leafwilds",
                                race = "High-Elf",
                                clazz = "Wizard",
                                level = 7
                            )
                        ))
                    }
                    CharacterSelectionScreen(
                        characters = characters,
                        onCharacterClicked = {},
                        onCharacterAddClicked = {
                            characters = characters +
                                Character(
                                    id = 1,
                                    name = "Terry",
                                    race = "Centaur",
                                    clazz = "Barbarian",
                                    level = 5
                                )
                            Log.d("CharacterSelectionActivity", "onCharacterAddClicked : ${characters.size}")
                        },
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}