package com.jlahougue.dndcompanion.feature_character_selection.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.core.presentation.theme.spacing
import com.jlahougue.dndcompanion.data_character.domain.model.Character
import com.jlahougue.dndcompanion.feature_character_selection.presentation.components.AddCharacterButton
import com.jlahougue.dndcompanion.feature_character_selection.presentation.components.CharacterCard

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CharacterSelectionScreen(
    characters: List<Character>,
    onCharacterClicked: (Character) -> Unit,
    onCharacterAddClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    FlowRow(
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        for (character in characters) {
            CharacterCard(
                character = character,
                modifier = Modifier
                    .padding(MaterialTheme.spacing.large)
                    .size(175.dp),
                onClick = onCharacterClicked
            )
        }
        AddCharacterButton(
            modifier = Modifier
                .padding(MaterialTheme.spacing.large)
                .size(175.dp),
            onClick = onCharacterAddClicked
        )
    }
}

@Preview(
    showBackground = true,
    device = Devices.TABLET
)
@Composable
fun CharacterSelectionPreview() {
    DnDCompanionTheme {
        val characters = mutableListOf<Character>()
        characters.add(
            Character(
                id = 0,
                name = "Daeron Leafwilds",
                race = "High-Elf",
                clazz = "Wizard",
                level = 7
            )
        )
        characters.add(
            Character(
                id = 1,
                name = "Terry",
                race = "Centaur",
                clazz = "Barbarian",
                level = 5
            )
        )
        CharacterSelectionScreen(
            characters = characters,
            onCharacterClicked = {},
            onCharacterAddClicked = {},
            modifier = Modifier
                .fillMaxSize()
        )
    }
}