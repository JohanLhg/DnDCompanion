package com.jlahougue.dndcompanion.feature_character_selection.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.core.presentation.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCharacterButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Box(modifier = modifier) {
        Card(
            onClick = onClick,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            elevation = CardDefaults.cardElevation(
                defaultElevation = MaterialTheme.spacing.medium
            )
        ) {
            Image(
                imageVector = Icons.Rounded.Add,
                contentDescription = stringResource(R.string.descr_character_image),
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Preview
@Composable
fun AddCharacterButtonPreview() {
    DnDCompanionTheme {
        AddCharacterButton()
    }
}