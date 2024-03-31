package com.jlahougue.profile_presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jlahougue.core_presentation.components.text_fileds.LinedTextField
import com.jlahougue.core_presentation.theme.spacing

@Composable
fun BoxedLinedTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(MaterialTheme.spacing.small)
            .fillMaxSize(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = MaterialTheme.spacing.medium
        )
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(MaterialTheme.spacing.small)
        )
        LinedTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(MaterialTheme.spacing.small)
                .fillMaxSize(),
            lineColor = MaterialTheme.colorScheme.secondary
        )
    }
}