package com.jlahougue.spells_presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.ability_domain.model.AbilityName
import com.jlahougue.ability_presentation.asShortUiText
import com.jlahougue.character_spell_domain.model.SpellcasterView
import com.jlahougue.core_domain.util.extension.toSignedString
import com.jlahougue.core_presentation.components.containers.StatBox
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.spells_presentation.R

@Composable
fun SpellcastingStats(
    spellcasting: SpellcasterView,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(IntrinsicSize.Max)
    ) {
        StatBox(
            label = stringResource(id = R.string.spellcasting_ability).uppercase(),
            modifier = Modifier
                .fillMaxWidth()
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
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        StatBox(
            label = stringResource(id = R.string.spell_save_dc).uppercase(),
            modifier = Modifier
                .fillMaxWidth()
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
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        StatBox(
            label = stringResource(id = R.string.spell_attack_bonus).uppercase(),
            modifier = Modifier
                .fillMaxWidth()
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
fun SpellcastingStatsPreview() {
    DnDCompanionTheme {
        SpellcastingStats(
            spellcasting = SpellcasterView(
                ability = AbilityName.STRENGTH,
                modifier = 3,
                saveDC = 13,
                attackBonus = 5
            ),
            modifier = Modifier
                .width(150.dp)
        )
    }
}