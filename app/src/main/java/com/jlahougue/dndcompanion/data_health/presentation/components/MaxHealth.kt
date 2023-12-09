package com.jlahougue.dndcompanion.data_health.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.presentation.theme.spacing
import com.jlahougue.dndcompanion.data_health.domain.model.Health

@Composable
fun MaxHealth(
    health: Health,
    modifier: Modifier = Modifier
) {
    DividerTitle(
        title = stringResource(R.string.health_max),
        modifier = Modifier.fillMaxWidth()
    )
    BasicTextField(
        value = "0",
        onValueChange = {},
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