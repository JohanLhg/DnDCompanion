package com.jlahougue.character_spell_presentation.dialog.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.jlahougue.character_spell_domain.model.SpellState
import com.jlahougue.character_spell_presentation.util.asUiText
import com.jlahougue.core_presentation.theme.spacing

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
        onExpandedChange = { onOpenedChange(it) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .width(150.dp)
                .menuAnchor(type = MenuAnchorType.PrimaryNotEditable)
        ) {
            Text(
                text = state.asUiText().getString(),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.rotate(if (isOpened) 180f else 0f)
            )
        }

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
                            text = state.asUiText().getString(),
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