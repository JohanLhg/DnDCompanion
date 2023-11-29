package com.jlahougue.dndcompanion.feature_character_selection.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.core.presentation.theme.spacing
import com.jlahougue.dndcompanion.data_character.domain.model.Character

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterCard(
    character: Character,
    modifier: Modifier = Modifier,
    onClick: (Character) -> Unit = {}
) {
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
                Image(
                    painter = painterResource(R.drawable.daeron),
                    contentDescription = stringResource(R.string.descr_character_image),
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
                        text = "Lvl " + character.level + " " + character.race + " " + character.clazz,
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
            modifier = Modifier
                .padding(MaterialTheme.spacing.large)
                .size(175.dp)
        )
    }
}