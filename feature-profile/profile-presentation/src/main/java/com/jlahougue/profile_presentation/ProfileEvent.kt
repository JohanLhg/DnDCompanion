package com.jlahougue.profile_presentation

import android.net.Uri
import com.jlahougue.class_presentation.detail_dialog.ClassDialogEvent
import com.jlahougue.class_presentation.list_dialog.ClassListDialogEvent

sealed class ProfileEvent {
    data class OnImageSelected(val uri: Uri) : ProfileEvent()
    data class OnNameChanged(val name: String) : ProfileEvent()
    data class OnRaceChanged(val race: String) : ProfileEvent()
    data object OnClassDetailsOpened : ProfileEvent()
    data object OnClassListOpened : ProfileEvent()
    data class OnLevelChanged(val level: Int) : ProfileEvent()
    data object OnLevelUp : ProfileEvent()
    data class OnGenderChanged(val gender: String) : ProfileEvent()
    data class OnAgeChanged(val age: Int) : ProfileEvent()
    data class OnHeightChanged(val height: Double) : ProfileEvent()
    data class OnWeightChanged(val weight: Int) : ProfileEvent()
    data class OnBackgroundChanged(val background: String) : ProfileEvent()
    data class OnPersonalityChanged(val personality: String) : ProfileEvent()
    data class OnIdealsChanged(val ideals: String) : ProfileEvent()
    data class OnBondsChanged(val bonds: String) : ProfileEvent()
    data class OnFlawsChanged(val flaws: String) : ProfileEvent()
    data class OnClassListDialogEvent(val event: ClassListDialogEvent) : ProfileEvent()
    data class OnClassDialogEvent(val event: ClassDialogEvent) : ProfileEvent()
}