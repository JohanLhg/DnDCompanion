package com.jlahougue.dndcompanion.core.presentation.components.dropdown

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.jlahougue.core_domain.util.UiText
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDropDown(
    value: String,
    options: List<DropDownItem>,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable { expanded = !expanded }
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(1f)
        )
        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        options.forEach { option ->
            DropdownMenuItem(
                text = {
                    Text(
                        text = option.label.getString(),
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                onClick = {
                    onOptionSelected(option.value)
                    expanded = false
                }
            )
        }
    }
}

@Preview
@Composable
fun CustomDropDownPreview() {
    DnDCompanionTheme {
        CustomDropDown(
            value = stringResource(R.string.copper_pieces),
            options = listOf(
                DropDownItem(
                    UiText.StringResource(R.string.copper_pieces),
                    "CP"
                ),
                DropDownItem(
                    UiText.StringResource(R.string.silver_pieces),
                    "SP"
                ),
                DropDownItem(
                    UiText.StringResource(R.string.gold_pieces),
                    "GP"
                )
            ),
            onOptionSelected = {}
        )
    }
}