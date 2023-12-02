package com.jlahougue.dndcompanion.data_spell.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.core.presentation.theme.spacing

@Composable
fun SpellLevel() {
    Row(
        modifier = Modifier
            .height(50.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.spell_background_level),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxHeight()
        )
        Image(
            painter = painterResource(id = R.drawable.spell_background_total),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxHeight()
        )
        Image(
            painter = painterResource(id = R.drawable.spell_background_middle),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxHeight()
        )
        Image(
            painter = painterResource(id = R.drawable.spell_background_left),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxHeight()
        )
        Image(
            painter = painterResource(id = R.drawable.spell_background_end),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxHeight()
        )
    }
}

@Composable
fun SpellLevelAlt(
    level: Int,
    total: Int,
    left: Int,
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
            start.linkTo(textLeft.start)
            end.linkTo(textLeft.end)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        }

        constrain(textLeft) {
            top.linkTo(parent.top)
            start.linkTo(bgMiddle.end)
            end.linkTo(bgEnd.start)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }

        constrain(valueLeft) {
            top.linkTo(bgLeft.top)
            bottom.linkTo(bgLeft.bottom)
            start.linkTo(bgLeft.start)
            end.linkTo(bgLeft.end)
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
            text = level.toString(),
            style = MaterialTheme.typography.bodyMedium,
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
            text = "Total slots",
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .layoutId("textTotal")
        )
        Text(
            text = total.toString(),
            style = MaterialTheme.typography.bodyMedium,
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
            text = "Slots left",
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .layoutId("textLeft")
        )
        Text(
            text = left.toString(),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .layoutId("valueLeft")
                .padding(MaterialTheme.spacing.medium)
        )
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
fun SpellLevelPreview() {
    DnDCompanionTheme {
        SpellLevelAlt(
            level = 2,
            total = 5,
            left = 0,
            modifier = Modifier
        )
    }
}