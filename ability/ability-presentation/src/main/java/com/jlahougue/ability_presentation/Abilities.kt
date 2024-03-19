package com.jlahougue.ability_presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.ability_domain.model.AbilityName
import com.jlahougue.ability_domain.model.AbilityView
import com.jlahougue.core_domain.util.extension.toSignedString
import com.jlahougue.core_presentation.components.containers.FramedBox
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing

@Composable
fun Abilities(
    abilities: List<AbilityView>,
    modifier: Modifier = Modifier
) {
    FramedBox(
        title = stringResource(id = R.string.abilities),
        modifier = modifier
    ) {
        Row {
            Spacer(
                modifier = Modifier
                    .width(0.dp)
                    .weight(1f)
            )
            Text(
                text = stringResource(id = R.string.ability_modifier_short),
                modifier = Modifier.width(50.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = stringResource(id = R.string.ability_saving_throws_short),
                modifier = Modifier.width(50.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall
            )
        }
        for (ability in abilities)
            AbilityRow(ability)
    }
}

@Composable
fun AbilityRow(ability: AbilityView) {
    Column {
        HorizontalDivider(
            modifier = Modifier
                .padding(vertical = MaterialTheme.spacing.extraSmall)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = ability.name.getShortString(),
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier
                .width(0.dp)
                .weight(1f)
            )
            Text(
                text = ability.modifier.toSignedString(),
                modifier = Modifier.width(50.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = if (ability.proficiency) FontWeight.Bold else FontWeight.Normal
            )
            Text(
                text = ability.savingThrow.toSignedString(),
                modifier = Modifier.width(50.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = if (ability.proficiency) FontWeight.Bold else FontWeight.Normal
            )
        }
    }
}

@Preview(
    apiLevel = 33,
    showBackground = true,
    device = Devices.TABLET
)
@Composable
fun AbilitiesPreview() {
    DnDCompanionTheme {
        Abilities(
            abilities = getAbilitiesPreviewData(),
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .height(IntrinsicSize.Max)
        )
    }
}

fun getAbilitiesPreviewData() = listOf(
    AbilityView(
        1,
        AbilityName.STRENGTH,
        10,
        0,
        0,
        false
    ),
    AbilityView(
        1,
        AbilityName.DEXTERITY,
        14,
        2,
        2,
        false
    ),
    AbilityView(
        1,
        AbilityName.CONSTITUTION,
        12,
        1,
        1,
        false
    ),
    AbilityView(
        1,
        AbilityName.INTELLIGENCE,
        22,
        6,
        9,
        true
    ),
    AbilityView(
        1,
        AbilityName.WISDOM,
        15,
        2,
        5,
        true
    ),
    AbilityView(
        1,
        AbilityName.CHARISMA,
        16,
        3,
        3,
        false
    ),
)