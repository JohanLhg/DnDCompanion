package com.jlahougue.dndcompanion.data_character_spell.presentation.components.cantrips

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.core.presentation.theme.spacing

@Composable
fun CantripsBanner(
    modifier: Modifier = Modifier
) {
    val spacing = MaterialTheme.spacing
    val constraints = ConstraintSet {
        val guideline = createGuidelineFromTop(spacing.small)

        val bgLevel = createRefFor("bgLevel")
        val bgMiddle = createRefFor("bgMiddle")
        val bgLeft = createRefFor("bgLeft")
        val bgEnd = createRefFor("bgEnd")

        val valueLevel = createRefFor("valueLevel")
        val labelCantrips = createRefFor("labelCantrips")

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

        constrain(bgMiddle) {
            top.linkTo(guideline)
            bottom.linkTo(parent.bottom)
            start.linkTo(bgLevel.end)
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

        constrain(labelCantrips) {
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
            text = "0",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .layoutId("valueLevel")
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
            text = stringResource(id = R.string.cantrips).uppercase(),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .layoutId("labelCantrips")
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
fun CantripsBannerPreview() {
    DnDCompanionTheme {
        Column {
            CantripsBanner()
        }
    }
}