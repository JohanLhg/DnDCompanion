package com.jlahougue.spells_presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.character_spell_domain.model.CharacterSpellsStatsView
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.spells_presentation.R
import com.jlahougue.core_presentation.R as CoreR

@Composable
fun SpellStats(
    stats: CharacterSpellsStatsView,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        SpellsStat(
            text = stats.totalHighlighted.toString(),
            image = painterResource(id = CoreR.drawable.highlight),
            imageDescription = stringResource(
                id = R.string.highlighted_spells
            )
        )
        SpellsStat(
            text = stats.totalUnlocked.toString(),
            image = painterResource(id = CoreR.drawable.padlock_open),
            imageDescription = stringResource(
                id = R.string.unlocked_spells
            )
        )
        SpellsStat(
            text = stringResource(
                id = CoreR.string.quota,
                stats.totalPrepared,
                stats.maxPrepared
            ),
            image = painterResource(id = CoreR.drawable.tick),
            imageDescription = stringResource(
                id = R.string.prepared_spells
            )
        )
    }
}

@Composable
private fun SpellsStat(
    text: String,
    image: Painter,
    imageDescription: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(bottom = MaterialTheme.spacing.extraSmall)
    ) {
        Image(
            painter = image,
            contentDescription = imageDescription,
            modifier = Modifier
                .size(24.dp)
        )
        Text(
            text = text,
            modifier = modifier
                .padding(MaterialTheme.spacing.extraSmall),
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview
@Composable
fun SpellsStatsPreview() {
    DnDCompanionTheme {
        SpellStats(
            stats = CharacterSpellsStatsView(
                totalHighlighted = 2,
                totalUnlocked = 10,
                totalPrepared = 4,
                maxPrepared = 5
            )
        )
    }
}