package com.jlahougue.dndcompanion.data_ability.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.domain.util.extension.toSignedString
import com.jlahougue.dndcompanion.core.presentation.components.FramedBox
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.core.presentation.theme.spacing
import com.jlahougue.dndcompanion.data_ability.domain.model.AbilityName
import com.jlahougue.dndcompanion.data_ability.domain.model.AbilityView
import com.jlahougue.dndcompanion.data_ability.domain.use_case.AbilityEvent

@Composable
fun Abilities(
    abilities: List<AbilityView>,
    onEvent: (AbilityEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    FramedBox(
        title = stringResource(id = R.string.abilities),
        modifier = modifier
    ) {
        Column {
            Row {
                Spacer(modifier = Modifier
                    .width(0.dp)
                    .weight(1f))
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
}

@Composable
fun AbilityRow(ability: AbilityView) {
    Column {
        Divider(
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

@Composable
fun AbilityRowAlt(name: String, imageId: Int, mod: Int = 0, st: Int = 0) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = name,
            modifier = Modifier
                .size(25.dp)
                .padding(2.dp)
        )
        Text(
            text = "+$mod",
            modifier = Modifier
                .width(50.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = "+$st",
            modifier = Modifier.width(50.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview(
    showBackground = true,
    device = Devices.TABLET
)
@Composable
fun AbilitiesPreview() {
    DnDCompanionTheme {
        Abilities(
            abilities = getAbilitiesPreviewData(),
            onEvent = {},
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .height(IntrinsicSize.Max)
        )
    }
}

fun getAbilitiesPreviewData() = listOf(
    AbilityView(1, AbilityName.STRENGTH, 10, 0, 0, false),
    AbilityView(1, AbilityName.DEXTERITY, 14, 2, 2, false),
    AbilityView(1, AbilityName.CONSTITUTION, 12, 1, 1, false),
    AbilityView(1, AbilityName.INTELLIGENCE, 22, 6, 9, true),
    AbilityView(1, AbilityName.WISDOM, 15, 2, 5, true),
    AbilityView(1, AbilityName.CHARISMA, 16, 3, 3, false),
)