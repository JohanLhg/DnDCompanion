package com.jlahougue.character_selection_presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.character_domain.model.Character
import com.jlahougue.character_domain.use_case.LoadImageState
import com.jlahougue.character_selection_presentation.R
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun CharacterCard(
    character: Character,
    getImage: (Long) -> StateFlow<LoadImageState>,
    modifier: Modifier = Modifier,
    onClick: (Character) -> Unit = {}
) {
    val imageState = getImage(character.id).collectAsState()
    Box(
        modifier = modifier
    ) {
        Card(
            onClick = { onClick(character) },
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            elevation = CardDefaults.cardElevation(
                defaultElevation = MaterialTheme.spacing.medium
            )
        ) {
            Box {
                CharacterImage(
                    state = imageState.value,
                    modifier = Modifier.fillMaxSize()
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black
                                )
                            )
                        )
                        .padding(MaterialTheme.spacing.small)
                        .padding(top = MaterialTheme.spacing.large)
                ) {
                    Text(
                        text = character.name,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        ),
                    )
                    Text(
                        text = stringResource(
                            id = R.string.character_subtitle,
                            character.race,
                            character.clazz,
                            character.level
                        ),
                        style = MaterialTheme.typography.bodySmall.copy(
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    )
                }
            }
        }
    }
}

@Preview(
    apiLevel = 33,
    showBackground = true,
    device = Devices.TABLET
)
@Composable
fun CharacterCardPreview() {
    DnDCompanionTheme {
        CharacterCard(
            character = Character(
                1,
                "Daeron Leafwilds",
                "High-Elf",
                "Wizard",
                7,
                "M",
                412,
                1.74,
                71,
                "",
                "",
                "",
                "",
                "",
                "",
                false,
            ),
            getImage = { _ -> MutableStateFlow(LoadImageState()) },
            modifier = Modifier
                .padding(MaterialTheme.spacing.large)
                .size(175.dp)
        )
    }
}