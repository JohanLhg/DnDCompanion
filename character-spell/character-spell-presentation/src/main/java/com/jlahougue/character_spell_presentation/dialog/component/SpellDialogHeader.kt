package com.jlahougue.character_spell_presentation.dialog.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import com.jlahougue.character_spell_domain.model.SpellState
import com.jlahougue.character_spell_presentation.R
import com.jlahougue.character_spell_presentation.components.ComponentImage
import com.jlahougue.character_spell_presentation.dialog.SpellDialogEvent
import com.jlahougue.character_spell_presentation.dialog.SpellDialogState
import com.jlahougue.character_spell_presentation.util.asUiText

@Composable
fun RowScope.SpellDialogHeader(
    state: SpellDialogState,
    onEvent: (SpellDialogEvent) -> Unit
) {
    val spell = state.spell!!
    Text(
        text = if (spell.level == 0) stringResource(R.string.spell_hdr_name_cantrip, spell.name)
        else stringResource(R.string.spell_hdr_name, spell.level, spell.name),
        style = if (spell.state == SpellState.HIGHLIGHTED)
            MaterialTheme.typography.titleMedium.copy(
                textDecoration = TextDecoration.Underline
            )
        else MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onPrimary
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
                    text = spell.state.asUiText().getString(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimary
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
                onStateChange = {
                    onEvent(SpellDialogEvent.OnStateChange(spell, it))
                },
                spellLevel = spell.level
            )
        }
    }
}