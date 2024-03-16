package com.jlahougue.profile_presentation

sealed class ProfileEvent {
    data object OnImageClicked : ProfileEvent()
    data class OnNameChanged(val name: String) : ProfileEvent()
    data class OnRaceChanged(val race: String) : ProfileEvent()
    data object OnClassListOpened : ProfileEvent()
    data class OnLevelChanged(val level: Int) : ProfileEvent()
    data object OnLevelUp : ProfileEvent()
    data class OnGenderChanged(val gender: String) : ProfileEvent()
    data class OnAgeChanged(val age: Int) : ProfileEvent()
    data class OnHeightChanged(val height: Double) : ProfileEvent()
    data class OnWeightChanged(val weight: Int) : ProfileEvent()
    data class OnBackgroundChanged(val background: String) : ProfileEvent()
    data class OnPersonalityChanged(val personality: String) : ProfileEvent()
}