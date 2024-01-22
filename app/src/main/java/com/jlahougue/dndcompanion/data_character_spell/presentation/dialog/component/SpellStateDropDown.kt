package com.jlahougue.dndcompanion.data_character_spell.presentation.dialog.component

import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpellStateDropDown(
    isOpened: Boolean,
    onOpenedChange: (Boolean) -> Unit,
    state: SpellState,
    onStateChange: (SpellState) -> Unit,
    spellLevel: Int
) {
    var states by remember {
        mutableStateOf(SpellState.entries.toList())
    }
    ExposedDropdownMenuBox(
        expanded = isOpened,
        onExpandedChange = { onOpenedChange(it) },
        modifier = Modifier
            .width(200.dp)
    ) {
        TextField(
            value = state.label.getString(),
            onValueChange = {},
            textStyle = MaterialTheme.typography.bodySmall,
            readOnly = true,
            trailingIcon = {
                TrailingIcon(expanded = isOpened)
            },
            modifier = Modifier.menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = isOpened,
            onDismissRequest = { onOpenedChange(false) }
        ) {
            if (spellLevel == 0) {
                states = states.filter {
                    it == SpellState.LOCKED
                            || it == SpellState.HIGHLIGHTED
                            || it == SpellState.ALWAYS_PREPARED
                }
            }

            states.forEach { state ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = state.label.getString(),
                            style = MaterialTheme.typography.bodySmall
                        )
                    },
                    onClick = {
                        onStateChange(state)
                        onOpenedChange(false)
                    }
                )
            }
        }
    }
}