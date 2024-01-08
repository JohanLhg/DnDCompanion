package com.jlahougue.dndcompanion.data_character_spell.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.core.presentation.theme.spacing
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellSlotView
import com.jlahougue.dndcompanion.feature_spells.presentation.SpellEvent

@Composable
fun SpellLevelBanner(
    spellSlot: SpellSlotView,
    onEvent: (SpellEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = MaterialTheme.spacing
    val constraints = ConstraintSet {
        val guideline = createGuidelineFromTop(spacing.small)

        val bgLevel = createRefFor("bgLevel")
        val bgTotal = createRefFor("bgTotal")
        val bgMiddle = createRefFor("bgMiddle")
        val bgLeft = createRefFor("bgLeft")
        val bgEnd = createRefFor("bgEnd")

        val valueLevel = createRefFor("valueLevel")
        val textTotal = createRefFor("textTotal")
        val valueTotal = createRefFor("valueTotal")
        val textLeft = createRefFor("textLeft")
        val valueLeft = createRefFor("valueLeft")

        constrain(bgLevel) {
            top.linkTo(guideline)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            width = Dimension.wrapContent
            height = Dimension.fillToConstraints
        }

        constrain(valueLevel) {
            top.linkTo(bgLevel.top)
            bottom.linkTo(bgLevel.bottom)
            start.linkTo(bgLevel.start)
            end.linkTo(bgLevel.end)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }

        constrain(bgTotal) {
            top.linkTo(guideline)
            bottom.linkTo(parent.bottom)
            start.linkTo(textTotal.start)
            end.linkTo(textTotal.end)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        }

        constrain(textTotal) {
            top.linkTo(parent.top)
            start.linkTo(bgLevel.end)
            end.linkTo(bgMiddle.start)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }

        constrain(valueTotal) {
            top.linkTo(bgTotal.top)
            bottom.linkTo(bgTotal.bottom)
            start.linkTo(bgTotal.start)
            end.linkTo(bgTotal.end)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }

        constrain(bgMiddle) {
            top.linkTo(guideline)
            bottom.linkTo(parent.bottom)
            start.linkTo(bgTotal.end)
            width = Dimension.wrapContent
            height = Dimension.fillToConstraints
        }

        constrain(bgLeft) {
            top.linkTo(guideline)
            bottom.linkTo(parent.bottom)
            start.linkTo(bgMiddle.end)
            end.linkTo(bgEnd.start)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        }

        constrain(textLeft) {
            top.linkTo(parent.top)
            start.linkTo(bgMiddle.end)
            end.linkTo(bgEnd.end)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }

        constrain(valueLeft) {
            top.linkTo(guideline)
            bottom.linkTo(parent.bottom)
            start.linkTo(bgMiddle.end)
            end.linkTo(bgEnd.end)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }

        constrain(bgEnd) {
            top.linkTo(guideline)
            bottom.linkTo(parent.bottom)
            end.linkTo(parent.end)
            start.linkTo(bgLeft.end)
            width = Dimension.wrapContent
            height = Dimension.fillToConstraints
        }
    }
    ConstraintLayout(
        constraintSet = constraints,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.spell_background_level),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .layoutId("bgLevel")
        )
        Text(
            text = spellSlot.level.toString(),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .layoutId("valueLevel")
                .padding(MaterialTheme.spacing.medium)
        )
        Image(
            painter = painterResource(id = R.drawable.spell_background_total),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .layoutId("bgTotal")
        )
        Text(
            text = stringResource(id = R.string.spell_total_slots),
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .layoutId("textTotal")
        )
        Text(
            text = spellSlot.total.toString(),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .layoutId("valueTotal")
                .padding(MaterialTheme.spacing.medium)
        )
        Image(
            painter = painterResource(id = R.drawable.spell_background_middle),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .layoutId("bgMiddle")
        )
        Image(
            painter = painterResource(id = R.drawable.spell_background_left),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .layoutId("bgLeft")
        )
        Text(
            text = stringResource(id = R.string.spell_slots_left),
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .layoutId("textLeft")
                .padding(end = MaterialTheme.spacing.small)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .layoutId("valueLeft")
                .padding(vertical = MaterialTheme.spacing.small)
                .padding(end = MaterialTheme.spacing.small)
                .height(IntrinsicSize.Min)
        ) {
            Image(
                painter = painterResource(id = R.drawable.chevron_left),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                alpha = if (spellSlot.left <= 0) 0.2f else 1f,
                modifier = Modifier
                    .height(35.dp)
                    .clickable(
                        enabled = spellSlot.left > 0,
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                        onClick = {
                            onEvent(SpellEvent.OnSlotUsed(spellSlot))
                        },
                    )
                    .padding(MaterialTheme.spacing.small)
            )
            Text(
                text = spellSlot.left.toString(),
                style = MaterialTheme.typography.titleMedium
            )
            Image(
                painter = painterResource(id = R.drawable.chevron_right),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                alpha = if (spellSlot.left >= spellSlot.total) 0.2f else 1f,
                modifier = Modifier
                    .height(35.dp)
                    .clickable(
                        enabled = spellSlot.left < spellSlot.total,
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                        onClick = {
                            onEvent(SpellEvent.OnSlotRestored(spellSlot))
                        },
                    )
                    .padding(MaterialTheme.spacing.small)
            )
        }
        Image(
            painter = painterResource(id = R.drawable.spell_background_end),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .layoutId("bgEnd")
        )
    }
}

@Preview(
    showBackground = true,
    device = Devices.TABLET
)
@Composable
fun SpellLevelBannerPreview() {
    DnDCompanionTheme {
        Column {
            SpellLevelBanner(
                spellSlot = SpellSlotView(
                    cid = 1,
                    level = 2,
                    total = 4,
                    left = 4
                ),
                onEvent = {},
            )
        }
    }
}