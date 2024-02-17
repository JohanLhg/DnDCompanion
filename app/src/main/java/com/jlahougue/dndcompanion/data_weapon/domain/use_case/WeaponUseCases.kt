package com.jlahougue.dndcompanion.data_weapon.domain.use_case

data class WeaponUseCases(
    val getWeapon: GetWeapon,
    val getWeapons: GetWeapons,
    val getWeaponsOwned: GetWeaponsOwned,
    val getWeaponStats: GetWeaponStats,
    val saveWeapon: SaveWeapon,
)
