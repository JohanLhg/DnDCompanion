package com.jlahougue.dndcompanion.data_weapon.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jlahougue.dndcompanion.data_weapon.domain.model.CharacterWeapon
import com.jlahougue.dndcompanion.data_weapon.domain.model.Weapon
import com.jlahougue.dndcompanion.data_weapon.domain.model.WeaponProperty

@Dao
interface WeaponLocalDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weapon: Weapon): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProperties(weaponProperties: List<WeaponProperty>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characterWeapon: CharacterWeapon): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characterWeapons: List<CharacterWeapon>)

    @Query("SELECT weapon_name FROM weapon")
    suspend fun getNames(): List<String>
}