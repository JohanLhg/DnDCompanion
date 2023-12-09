package com.jlahougue.dndcompanion.data_health.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.core.presentation.theme.Green
import com.jlahougue.dndcompanion.core.presentation.theme.Red
import com.jlahougue.dndcompanion.core.presentation.theme.spacing


@Composable
fun TextFieldWithIncrements(
    value: String,
    onValueChange: (String) -> Unit,
    onPlusClick: () -> Unit,
    plusDescription: String,
    onMinusClick: () -> Unit,
    minusDescription: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(1f)
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .width(75.dp)
                    .padding(horizontal = MaterialTheme.spacing.small)
                    .border(
                        1.dp,
                        Color.Gray,
                        RoundedCornerShape(5.dp)
                    )
                    .padding(horizontal = MaterialTheme.spacing.small)
            )
        }
        Column(
            modifier = Modifier.width(50.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.arrow_up),
                colorFilter = ColorFilter.tint(Green),
                contentDescription = plusDescription,
                modifier = Modifier
                    .requiredWidth(50.dp)
                    .height(30.dp)
                    .padding(MaterialTheme.spacing.extraSmall)
                    .border(
                        1.dp,
                        Green,
                        RoundedCornerShape(5.dp)
                    )
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(
                            bounded = true,
                            color = Green
                        ),
                        onClick = onPlusClick,
                    )
                    .padding(MaterialTheme.spacing.extraSmall)
            )
            Image(
                painter = painterResource(id = R.drawable.arrow_down),
                colorFilter = ColorFilter.tint(Red),
                contentDescription = minusDescription,
                modifier = Modifier
                    .requiredWidth(50.dp)
                    .height(30.dp)
                    .padding(MaterialTheme.spacing.extraSmall)
                    .border(
                        1.dp,
                        Red,
                        RoundedCornerShape(5.dp)
                    )
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(
                            bounded = true,
                            color = Red
                        ),
                        onClick = onMinusClick,
                    )
                    .padding(MaterialTheme.spacing.extraSmall)
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun TextFieldWithIncrementsPreview() {
    DnDCompanionTheme {
        TextFieldWithIncrements(
            value = "999",
            onValueChange = {},
            onPlusClick = {},
            plusDescription = "plus",
            onMinusClick = {},
            minusDescription = "minus"
        )
    }
}