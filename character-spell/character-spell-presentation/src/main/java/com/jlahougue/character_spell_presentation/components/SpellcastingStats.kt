package com.jlahougue.character_spell_presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.ability_domain.model.AbilityName
import com.jlahougue.ability_presentation.asShortUiText
import com.jlahougue.character_spell_domain.model.SpellcasterView
import com.jlahougue.character_spell_presentation.R
import com.jlahougue.core_domain.util.extension.toSignedString
import com.jlahougue.core_presentation.components.containers.StatBox
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing

@Composable
fun SpellcastingStatsColumn(
    spellcasting: SpellcasterView,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StatBox(
            label = stringResource(id = R.string.spellcasting_ability).uppercase(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text(
                    text = spellcasting.modifier.toSignedString(),
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.titleMedium.copy(
                        textAlign = TextAlign.Center
                    )
                )
                Text(
                    text = spellcasting.ability.asShortUiText().getString(),
                    modifier = Modifier
                        .fillMaxWidth(),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = MaterialTheme.typography.bodySmall.copy(
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
        Spacer(modifier = Modifier.size(MaterialTheme.spacing.medium))
        StatBox(
            label = stringResource(id = R.string.spell_save_dc).uppercase(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = spellcasting.saveDC.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = MaterialTheme.spacing.small),
                style = MaterialTheme.typography.titleMedium.copy(
                    textAlign = TextAlign.Center
                )
            )
        }
        Spacer(modifier = Modifier.size(MaterialTheme.spacing.medium))
        StatBox(
            label = stringResource(id = R.string.spell_attack_bonus).uppercase(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = spellcasting.attackBonus.toSignedString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = MaterialTheme.spacing.small),
                style = MaterialTheme.typography.titleMedium.copy(
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}

@Composable
fun SpellcastingStatsRow(
    spellcasting: SpellcasterView,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        StatBox(
            label = stringResource(id = R.string.spellcasting_ability).uppercase(),
            modifier = Modifier
                .weight(1f)
                .widthIn(max = 200.dp)
                .padding(MaterialTheme.spacing.small)
        ) {
            Column {
                Text(
                    text = spellcasting.modifier.toSignedString(),
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.titleMedium.copy(
                        textAlign = TextAlign.Center
                    )
                )
                Text(
                    text = spellcasting.ability.asShortUiText().getString(),
                    modifier = Modifier
                        .fillMaxWidth(),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = MaterialTheme.typography.bodySmall.copy(
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
        StatBox(
            label = stringResource(id = R.string.spell_save_dc).uppercase(),
            modifier = Modifier
                .weight(1f)
                .widthIn(max = 200.dp)
                .padding(MaterialTheme.spacing.small)
        ) {
            Text(
                text = spellcasting.saveDC.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = MaterialTheme.spacing.small),
                style = MaterialTheme.typography.titleMedium.copy(
                    textAlign = TextAlign.Center
                )
            )
        }
        StatBox(
            label = stringResource(id = R.string.spell_attack_bonus).uppercase(),
            modifier = Modifier
                .weight(1f)
                .widthIn(max = 200.dp)
                .padding(MaterialTheme.spacing.small)
        ) {
            Text(
                text = spellcasting.attackBonus.toSignedString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = MaterialTheme.spacing.small),
                style = MaterialTheme.typography.titleMedium.copy(
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}

@Preview
@Composable
fun SpellcastingStatsColumnPreview() {
    DnDCompanionTheme {
        SpellcastingStatsColumn(
            spellcasting = SpellcasterView(
                ability = AbilityName.STRENGTH,
                modifier = 3,
                saveDC = 13,
                attackBonus = 5
            )
        )
    }
}

@Preview(widthDp = 1500)
@Composable
fun SpellcastingStatsRowPreview() {
    DnDCompanionTheme {
        SpellcastingStatsRow(
            spellcasting = SpellcasterView(
                ability = AbilityName.STRENGTH,
                modifier = 3,
                saveDC = 13,
                attackBonus = 5
            )
        )
    }
}