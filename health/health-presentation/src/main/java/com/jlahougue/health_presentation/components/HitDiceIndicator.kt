package com.jlahougue.health_presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.core_presentation.components.dropdown.CustomDropDown
import com.jlahougue.core_presentation.components.dropdown.DropDownItem
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.core_presentation.util.UiText
import com.jlahougue.health_domain.model.HitDiceView
import com.jlahougue.health_presentation.HealthEvent
import com.jlahougue.health_presentation.R

@Composable
fun HitDiceIndicator(
    hitDice: HitDiceView,
    onEvent: (HealthEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val options by remember {
    mutableStateOf(
        listOf(
            DropDownItem(
                value = "d4",
                label = UiText.DynamicString("d4")
            ),
            DropDownItem(
                value = "d6",
                label = UiText.DynamicString("d6")
            ),
            DropDownItem(
                value = "d8",
                label = UiText.DynamicString("d8")
            ),
            DropDownItem(
                value = "d10",
                label = UiText.DynamicString("d10")
            ),
            DropDownItem(
                value = "d12",
                label = UiText.DynamicString("d12")
            ),
            DropDownItem(
                value = "d20",
                label = UiText.DynamicString("d20")
            )
        )
    )
}
    Column(
        modifier = modifier
    ) {
        DividerTitle(
            title = stringResource(R.string.hit_dice),
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = MaterialTheme.spacing.small)
        ) {
            Text(
                text = stringResource(id = R.string.hit_dice),
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(
                modifier = Modifier
                .width(MaterialTheme.spacing.medium)
                .weight(1f)
            )
            CustomDropDown(
                value = hitDice.dice,
                options = options,
                onOptionSelected = {
                    onEvent(HealthEvent.OnHitDiceChange(it))
                },
                modifier = Modifier.width(IntrinsicSize.Max)
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(id = R.string.hit_dice_left),
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(
                modifier = Modifier
                .width(MaterialTheme.spacing.medium)
                .weight(1f)
            )
            Text(
                text = "${hitDice.current}/${hitDice.max}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(
    apiLevel = 33,
    showBackground = true,
)
@Composable
fun HitDiceIndicatorPreview() {
    DnDCompanionTheme {
        HitDiceIndicator(
            hitDice = HitDiceView(
                dice = "d8",
                current = 2,
                max = 8
            ),
            onEvent = {},
            modifier = Modifier
                .width(200.dp)
        )
    }
}