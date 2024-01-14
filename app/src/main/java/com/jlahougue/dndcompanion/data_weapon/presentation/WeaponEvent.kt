package com.jlahougue.dndcompanion.data_weapon.presentation

import com.jlahougue.dndcompanion.data_weapon.domain.model.WeaponInfo

sealed class WeaponEvent {
    data class OnWeaponClicked(val weapon: WeaponInfo) : WeaponEvent()
}