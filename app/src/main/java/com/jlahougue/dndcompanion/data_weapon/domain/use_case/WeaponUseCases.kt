package com.jlahougue.dndcompanion.data_weapon.domain.use_case

data class WeaponUseCases(
    val getWeapon: GetWeapon,
    val getWeaponsOwned: GetWeaponsOwned,
    val saveWeapon: SaveWeapon,
)
