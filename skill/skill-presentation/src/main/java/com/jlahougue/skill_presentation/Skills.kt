package com.jlahougue.skill_presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.ability_domain.model.AbilityName
import com.jlahougue.ability_presentation.asShortUiText
import com.jlahougue.core_domain.util.extension.toSignedString
import com.jlahougue.core_presentation.components.containers.FramedBox
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.skill_domain.model.SkillName
import com.jlahougue.skill_domain.model.SkillView

@Composable
fun Skills(
    skills: List<SkillView>,
    modifier: Modifier = Modifier
) {
    FramedBox(
        title = "Skills",
        contentPadding = PaddingValues(0.dp),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            skills.forEachIndexed { index, skill ->
                SkillRow(
                    skill = skill,
                    background = if (index % 2 == 0) MaterialTheme.colorScheme.surface
                    else MaterialTheme.colorScheme.surfaceVariant
                )
            }
        }
    }
}

@Composable
fun SkillRow(
    skill: SkillView,
    background: Color,
    modifier: Modifier = Modifier
) {
    val style = if (skill.proficiency) MaterialTheme.typography.bodySmall.copy(
        fontWeight = FontWeight.Bold
    )
    else MaterialTheme.typography.bodySmall
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(background)
            .padding(MaterialTheme.spacing.extraSmall)
    ) {
        Text(
            text = skill.modifier.toSignedString(),
            modifier = Modifier
                .width(30.dp),
            textAlign = TextAlign.Center,
            style = style
        )
        Text(
            text = stringResource(
                id = R.string.skill_display,
                skill.name.asUiText().getString(),
                skill.modifierType.asShortUiText().getString()
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp),
            textAlign = TextAlign.Center,
            style = style
        )
    }
}

@Preview(
    apiLevel = 33,
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
    SkillView(1, SkillName.ATHLETICS, AbilityName.STRENGTH, 0, true),
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