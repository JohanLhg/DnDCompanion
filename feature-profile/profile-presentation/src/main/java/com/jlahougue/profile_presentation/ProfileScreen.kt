package com.jlahougue.profile_presentation

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.character_domain.model.Character
import com.jlahougue.character_presentation.CharacterImage
import com.jlahougue.class_presentation.detail_dialog.ClassDialog
import com.jlahougue.class_presentation.list_dialog.ClassListDialog
import com.jlahougue.core_domain.util.extension.toDoubleOrZero
import com.jlahougue.core_domain.util.extension.toIntOrZero
import com.jlahougue.core_presentation.components.containers.MaxedRow
import com.jlahougue.core_presentation.components.text_fileds.CustomOutlinedTextField
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.profile_presentation.components.BoxedLinedTextField
import com.jlahougue.core_presentation.R as CoreR

@Composable
fun ProfileScreen(
    state: ProfileState,
    onEvent: (ProfileEvent) -> Unit
) {
    val valueStyle = MaterialTheme.typography.bodyMedium
    Column {
        MaxedRow(
            modifier = Modifier
                .padding(vertical = MaterialTheme.spacing.extraSmall)
        ) { height ->
            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent()
            ) { uri: Uri? ->
                if (uri != null) onEvent(ProfileEvent.OnImageSelected(uri))
            }
            Card(
                onClick = {
                    launcher.launch("image/*")
                },
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .size(height)
                    .padding(MaterialTheme.spacing.small),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = MaterialTheme.spacing.medium
                )
            ) {
                CharacterImage(
                    imageUri = state.imageUri,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .width(IntrinsicSize.Max)
                    .fillMaxHeight()
            ) {
                Title(text = stringResource(id = R.string.character_name))
                Title(text = stringResource(id = R.string.character_race))
                Title(text = stringResource(id = R.string.character_class))
                Title(text = stringResource(id = R.string.character_level))
            }
            Column(
                modifier = Modifier
                    .defaultMinSize(minWidth = 100.dp)
                    .width(IntrinsicSize.Max)
            ) {
                Field(
                    value = state.character.name,
                    onValueChange = { onEvent(ProfileEvent.OnNameChanged(it)) }
                )
                Field(
                    value = state.character.race,
                    onValueChange = { onEvent(ProfileEvent.OnRaceChanged(it)) }
                )
                MaxedRow(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.extraSmall)
                ) { height ->
                    Text(
                        text = state.character.clazz,
                        style = valueStyle,
                        modifier = Modifier
                            .clickable { onEvent(ProfileEvent.OnClassDetailsOpened) }
                            .padding(horizontal = MaterialTheme.spacing.small)
                            .padding(vertical = MaterialTheme.spacing.extraSmall)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        onClick = { onEvent(ProfileEvent.OnClassListOpened) },
                        modifier = Modifier.size(height)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = stringResource(id = R.string.class_list)
                        )
                    }
                }
                MaxedRow(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.extraSmall)
                ) { height ->
                    CustomOutlinedTextField(
                        value = state.character.level.toString(),
                        onValueChange = { onEvent(ProfileEvent.OnLevelChanged(it.toIntOrZero())) },
                        modifier = Modifier.weight(1f),
                        verticalPadding = MaterialTheme.spacing.extraSmall,
                        textStyle = valueStyle
                    )
                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
                    IconButton(
                        onClick = { onEvent(ProfileEvent.OnLevelUp) },
                        modifier = Modifier.size(height)
                    ) {
                        Image(
                            painter = painterResource(CoreR.drawable.level_up),
                            contentDescription = stringResource(id = R.string.level_up)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
            Column(
                modifier = Modifier
                    .width(IntrinsicSize.Max)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                Title(text = stringResource(id = R.string.character_gender))
                Title(text = stringResource(id = R.string.character_age))
                Title(text = stringResource(id = R.string.character_height))
                Title(text = stringResource(id = R.string.character_weight))
            }
            Column(
                modifier = Modifier
                    .defaultMinSize(minWidth = 70.dp)
                    .width(IntrinsicSize.Max)
            ) {
                Field(
                    value = state.character.gender,
                    onValueChange = { onEvent(ProfileEvent.OnGenderChanged(it)) }
                )
                Field(
                    value = state.character.age.toString(),
                    onValueChange = { onEvent(ProfileEvent.OnAgeChanged(it.toIntOrZero())) }
                )
                Field(
                    value = state.character.height.toString(),
                    onValueChange = { onEvent(ProfileEvent.OnHeightChanged(it.toDoubleOrZero())) }
                )
                Field(
                    value = state.character.weight.toString(),
                    onValueChange = { onEvent(ProfileEvent.OnWeightChanged(it.toIntOrZero())) }
                )
            }
        }
        HorizontalDivider()
        Row {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                BoxedLinedTextField(
                    label = state.character.backgroundTitle.ifEmpty {
                        stringResource(id = R.string.character_biography)
                    },
                    value = state.character.background,
                    onValueChange = { onEvent(ProfileEvent.OnBackgroundChanged(it)) },
                    modifier = Modifier.weight(2f)
                )
                BoxedLinedTextField(
                    label = stringResource(id = R.string.character_personality),
                    value = state.character.personality,
                    onValueChange = { onEvent(ProfileEvent.OnPersonalityChanged(it)) },
                    modifier = Modifier.weight(1f)
                )
            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
                BoxedLinedTextField(
                    label = stringResource(id = R.string.character_ideals),
                    value = state.character.ideals,
                    onValueChange = { onEvent(ProfileEvent.OnIdealsChanged(it)) },
                    modifier = Modifier.weight(1f)
                )
                BoxedLinedTextField(
                    label = stringResource(id = R.string.character_bonds),
                    value = state.character.bonds,
                    onValueChange = { onEvent(ProfileEvent.OnBondsChanged(it)) },
                    modifier = Modifier.weight(1f)
                )
                BoxedLinedTextField(
                    label = stringResource(id = R.string.character_flaws),
                    value = state.character.flaws,
                    onValueChange = { onEvent(ProfileEvent.OnFlawsChanged(it)) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
    ClassListDialog(
        state = state.classListDialog,
        onEvent = {
            onEvent(ProfileEvent.OnClassListDialogEvent(it))
        }
    )
    ClassDialog(
        state = state.classDialog,
        onEvent = {
            onEvent(ProfileEvent.OnClassDialogEvent(it))
        }
    )
}

@Composable
private fun Title(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier.padding(vertical = MaterialTheme.spacing.small)
    )
}

@Composable
fun Field(
    value: String,
    onValueChange: (String) -> Unit
) {
    CustomOutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .padding(MaterialTheme.spacing.extraSmall)
            .fillMaxWidth(),
        verticalPadding = MaterialTheme.spacing.extraSmall,
        textStyle = MaterialTheme.typography.bodyMedium
    )
}

@Preview(
    apiLevel = 33,
    showBackground = true,
    device = Devices.TABLET
)
@Composable
fun ProfileScreenPreview() {
    DnDCompanionTheme {
        ProfileScreen(
            state = ProfileState(
                character = Character(
                    name = "Gandalf",
                    race = "Human",
                    clazz = "Wizard",
                    level = 20,
                    gender = "M",
                    age = 100,
                    height = 1.80,
                    weight = 80,
                    backgroundTitle = "Tourmenté"
                ),
                imageUri = null,
            ),
            onEvent = {}
        )
    }
}