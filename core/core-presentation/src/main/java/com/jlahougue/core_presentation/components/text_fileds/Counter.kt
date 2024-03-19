package com.jlahougue.core_presentation.components.text_fileds

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jlahougue.core_presentation.R
import com.jlahougue.core_presentation.theme.spacing

@Composable
fun Counter(
    quantity: Int,
    onQuantityChanged: (Int) -> Unit,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    borderColor: Color = Color.Gray
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        val focusManager = LocalFocusManager.current
        Image(
            painter = painterResource(id = R.drawable.chevron_left),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            colorFilter = ColorFilter.tint(color),
            alpha = if (quantity <= 0) 0.2f else 1f,
            modifier = Modifier
                .height(24.dp)
                .clickable(
                    enabled = quantity > 0,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = false),
                    onClick = {
                        focusManager.clearFocus()
                        onQuantityChanged(quantity - 1)
                    },
                )
                .padding(horizontal = MaterialTheme.spacing.extraSmall)
        )
        CustomOutlinedTextField(
            value = quantity.toString(),
            onValueChange = {
                onQuantityChanged(it.toIntOrNull() ?: 0)
            },
            borderColor = borderColor,
            horizontalPadding = 0.dp,
            textStyle = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .weight(1f)
        )
        Image(
            painter = painterResource(id = R.drawable.chevron_right),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            colorFilter = ColorFilter.tint(color),
            modifier = Modifier
                .height(24.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = false),
                    onClick = {
                        focusManager.clearFocus()
                        onQuantityChanged(quantity + 1)
                    },
                )
                .padding(horizontal = MaterialTheme.spacing.extraSmall)
        )
    }
}