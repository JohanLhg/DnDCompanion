package com.jlahougue.weapon_presentation

sealed class WeaponEvent {
    data class OnWeaponClicked(val weaponName: String) : WeaponEvent()
    data object OnAddWeaponClicked : WeaponEvent()
}