package com.jlahougue.character_spell_presentation.dialog.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.jlahougue.character_spell_domain.model.SpellState
import com.jlahougue.character_spell_presentation.R
import com.jlahougue.character_spell_presentation.components.ComponentImage
import com.jlahougue.character_spell_presentation.dialog.SpellDialogEvent
import com.jlahougue.character_spell_presentation.dialog.SpellDialogState
import com.jlahougue.core_presentation.theme.spacing

@Composable
fun SpellDialogHeader(
    state: SpellDialogState,
    onEvent: (SpellDialogEvent) -> Unit
) {
    val spell = state.spell!!
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = MaterialTheme.spacing.small)
    ) {
        Text(
            text = if (spell.level == 0) stringResource(R.string.spell_hdr_name_cantrip, spell.name)
            else stringResource(R.string.spell_hdr_name, spell.level, spell.name),
            style = MaterialTheme.typography.titleMedium,
            color = if (spell.state == SpellState.HIGHLIGHTED)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .padding(vertical = MaterialTheme.spacing.medium)
                .padding(horizontal = MaterialTheme.spacing.extraSmall)
        )
        if (spell.components.contains('V')) {
            ComponentImage(
                painter = painterResource(id = R.drawable.vocal),
                contentDescription = stringResource(id = R.string.vocal_component)
            )
        }
        if (spell.components.contains('S')) {
            ComponentImage(
                painter = painterResource(id = R.drawable.somatic),
                contentDescription = stringResource(id = R.string.somatic_component)
            )
        }
        if (spell.components.contains('M')) {
            ComponentImage(
                painter = painterResource(id = R.drawable.materials),
                contentDescription = stringResource(id = R.string.material_component)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        when (state.mode) {
            SpellDialogState.Mode.Display -> {
                if (spell.level > 0) {
                    Text(
                        text = spell.state.label.getString(),
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .padding(horizontal = MaterialTheme.spacing.medium)
                    )
                }
            }
            SpellDialogState.Mode.Edit -> {
                SpellStateDropDown(
                    isOpened = state.isStateDropdownOpened,
                    onOpenedChange = { opened ->
                        onEvent(SpellDialogEvent.OnStateDropdownOpen(opened))
                    },
                    state = spell.state,
                    onStateChange = { state ->
                        onEvent(SpellDialogEvent.OnStateChange(spell, state))
                    },
                    spellLevel = spell.level
                )
            }
        }
    }
}