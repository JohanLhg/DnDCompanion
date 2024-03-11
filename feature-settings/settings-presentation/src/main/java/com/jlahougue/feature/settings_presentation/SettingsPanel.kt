package com.jlahougue.feature.settings_presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.feature.settings_presentation.components.LanguageSelector
import com.jlahougue.feature.settings_presentation.components.SettingsButton
import com.jlahougue.feature.settings_presentation.components.UnitSystemSelector
import com.jlahougue.settings_domain.model.Language
import com.jlahougue.settings_domain.model.UnitSystem
import com.jlahougue.settings_presentation.R
import com.jlahougue.core_presentation.R as CoreR

@Composable
fun SettingsPanel(
    state: SettingsState,
    onEvent: (SettingsEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .width(IntrinsicSize.Max)
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = CoreR.drawable.logo_round_dnd),
                contentDescription = stringResource(id = CoreR.string.label_logo),
                modifier = Modifier
                    .size(150.dp)
                    .padding(MaterialTheme.spacing.large)
                    .align(Alignment.Center)
            )
        }
        Text(
            text = stringResource(id = R.string.language),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(MaterialTheme.spacing.small)
                .fillMaxWidth()
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            LanguageSelector(
                painter = painterResource(id = R.drawable.flag_us),
                label = stringResource(id = R.string.english),
                language = Language.ENGLISH,
                selectedLanguage = state.language,
                onEvent = { onEvent(SettingsEvent.OnLanguageChanged(it)) },
                modifier = Modifier
                    .weight(1f)
            )
            LanguageSelector(
                painter = painterResource(id = R.drawable.flag_fr),
                label = stringResource(id = R.string.french),
                language = Language.FRENCH,
                selectedLanguage = state.language,
                onEvent = { onEvent(SettingsEvent.OnLanguageChanged(it)) },
                modifier = Modifier
                    .weight(1f)
            )
        }
        Text(
            text = stringResource(id = R.string.unit_system),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(MaterialTheme.spacing.small)
                .fillMaxWidth()
        )
        Row {
            UnitSystemSelector(
                label = stringResource(id = R.string.imperial),
                unitSystem = UnitSystem.IMPERIAL,
                selectedUnitSystem = state.unitSystem,
                onEvent = { onEvent(SettingsEvent.OnUnitSystemChanged(it)) },
                modifier = Modifier.weight(1f)
            )
            UnitSystemSelector(
                label = stringResource(id = R.string.metric),
                unitSystem = UnitSystem.METRIC,
                selectedUnitSystem = state.unitSystem,
                onEvent = { onEvent(SettingsEvent.OnUnitSystemChanged(it)) },
                modifier = Modifier.weight(1f)
            )
        }
        Text(
            text = "Account",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(MaterialTheme.spacing.small)
                .fillMaxWidth()
        )
        SettingsButton(
            label = "change email address",
            icon = Icons.Filled.Email,
            onClick = { onEvent(SettingsEvent.OnEmailChange) }
        )
        SettingsButton(
            label = "change password",
            icon = Icons.Filled.Lock,
            onClick = { onEvent(SettingsEvent.OnPasswordChange) }
        )
        SettingsButton(
            label = "Sign out",
            icon = Icons.AutoMirrored.Filled.ExitToApp,
            onClick = { onEvent(SettingsEvent.OnSignOut) }
        )
        SettingsButton(
            label = "Switch character",
            icon = Icons.Filled.Person,
            onClick = { onEvent(SettingsEvent.OnCharacterSwitch) }
        )
    }
}

@Preview(
    apiLevel = 33,
    showBackground = true,
    device = Devices.TABLET
)
@Composable
fun SettingsPanelPreview() {
    DnDCompanionTheme {
        SettingsPanel(
            state = SettingsState(),
            onEvent = {  }
        )
    }
}