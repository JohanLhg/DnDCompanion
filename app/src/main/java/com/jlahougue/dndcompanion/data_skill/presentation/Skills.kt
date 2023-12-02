package com.jlahougue.dndcompanion.data_skill.presentation

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.domain.util.extension.toSignedString
import com.jlahougue.dndcompanion.core.presentation.components.FramedBox
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.data_ability.domain.model.AbilityName
import com.jlahougue.dndcompanion.data_skill.domain.model.SkillName
import com.jlahougue.dndcompanion.data_skill.domain.model.SkillView

@Composable
fun Skills(skills: List<SkillView>, modifier: Modifier = Modifier) {
    FramedBox(title = "Skills", modifier = modifier) {
        LazyColumn {
            items(skills) { skill ->
                SkillRow(skill)
            }
        }
    }
}

@Composable
fun SkillRow(skill: SkillView, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(vertical = 3.dp)
    ) {
        Text(
            text = skill.modifier.toSignedString(),
            modifier = Modifier
                .width(30.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = stringResource(
                id = R.string.skill_display,
                skill.name.getString(),
                skill.modifierType.getShortString()
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview(
    showBackground = true,
    device = Devices.TABLET
)
@Composable
fun SkillsPreview() {
    DnDCompanionTheme {
        Skills(
            skills = getSkillsPreviewData(),
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .height(IntrinsicSize.Min)
        )
    }
}

fun getSkillsPreviewData() = listOf(
    SkillView(1, SkillName.ACROBATICS, AbilityName.DEXTERITY, 2, false),
    SkillView(1, SkillName.ANIMAL_HANDLING, AbilityName.WISDOM, 5, false),
    SkillView(1, SkillName.ARCANA, AbilityName.INTELLIGENCE, 6, false),
    SkillView(1, SkillName.ATHLETICS, AbilityName.STRENGTH, 0, false),
    SkillView(1, SkillName.DECEPTION, AbilityName.CHARISMA, 3, false),
    SkillView(1, SkillName.HISTORY, AbilityName.INTELLIGENCE, 6, false),
    SkillView(1, SkillName.INSIGHT, AbilityName.WISDOM, 5, false),
    SkillView(1, SkillName.INTIMIDATION, AbilityName.CHARISMA, 3, false),
    SkillView(1, SkillName.INVESTIGATION, AbilityName.INTELLIGENCE, 6, false),
    SkillView(1, SkillName.MEDICINE, AbilityName.WISDOM, 5, false),
    SkillView(1, SkillName.NATURE, AbilityName.INTELLIGENCE, 6, false),
    SkillView(1, SkillName.PERCEPTION, AbilityName.WISDOM, 5, false),
    SkillView(1, SkillName.PERFORMANCE, AbilityName.CHARISMA, 3, false),
    SkillView(1, SkillName.PERSUASION, AbilityName.CHARISMA, 3, false),
    SkillView(1, SkillName.RELIGION, AbilityName.INTELLIGENCE, 6, false),
    SkillView(1, SkillName.SLEIGHT_OF_HAND, AbilityName.DEXTERITY, 2, false),
    SkillView(1, SkillName.STEALTH, AbilityName.DEXTERITY, 2, false),
    SkillView(1, SkillName.SURVIVAL, AbilityName.WISDOM, 5, false),
)