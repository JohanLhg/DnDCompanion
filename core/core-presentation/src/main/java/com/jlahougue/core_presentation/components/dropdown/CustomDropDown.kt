package com.jlahougue.core_presentation.components.dropdown

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.util.UiText

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
            value = "Copper pieces",
            options = listOf(
                DropDownItem(
                    UiText.DynamicString("Copper pieces"),
                    "CP"
                ),
                DropDownItem(
                    UiText.DynamicString("Silver pieces"),
                    "SP"
                ),
                DropDownItem(
                    UiText.DynamicString("Gold pieces"),
                    "GP"
                )
            ),
            onOptionSelected = {}
        )
    }
}