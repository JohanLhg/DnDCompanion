package com.jlahougue.dndcompanion.data_weapon.presentation

sealed class WeaponEvent {
    data class OnWeaponClicked(val weaponName: String) : WeaponEvent()
    data object OnAddWeaponClicked : WeaponEvent()
}