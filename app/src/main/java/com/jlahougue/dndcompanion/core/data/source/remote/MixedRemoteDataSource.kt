package com.jlahougue.dndcompanion.core.data.source.remote

import com.jlahougue.ability_data.source.remote.AbilityFirebaseDataSource
import com.jlahougue.authentication_data.source.AuthFirebaseDataSource
import com.jlahougue.character_data.source.remote.CharacterFirebaseDataSource
import com.jlahougue.class_data.source.remote.ClassMixedRemoteDataSource
import com.jlahougue.class_data.source.remote.subsource.ClassDnd5eDataSource
import com.jlahougue.class_data.source.remote.subsource.ClassOpen5eDataSource
import com.jlahougue.core_data_remote_instance.Dnd5eDataSource
import com.jlahougue.core_data_remote_instance.FirebaseDataSource
import com.jlahougue.core_data_remote_instance.Open5eDataSource
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_character_sheet.data.source.remote.CharacterSheetFirebaseDataSource
import com.jlahougue.dndcompanion.data_character_spell.data.source.remote.CharacterSpellFirebaseDataSource
import com.jlahougue.dndcompanion.data_currency.data.source.remote.MoneyFirebaseDataSource
import com.jlahougue.dndcompanion.data_damage_type.data.source.remote.DamageTypeDnd5eDataSource
import com.jlahougue.dndcompanion.data_health.data.source.remote.HealthFirebaseDataSource
import com.jlahougue.dndcompanion.data_item.data.source.remote.ItemFirebaseDataSource
import com.jlahougue.dndcompanion.data_property.data.source.remote.PropertyDnd5eDataSource
import com.jlahougue.dndcompanion.data_skill.data.source.remote.SkillFirebaseDataSource
import com.jlahougue.dndcompanion.data_spell.data.source.remote.SpellOpen5eDataSource
import com.jlahougue.dndcompanion.data_stats.data.source.remote.StatsFirebaseDataSource
import com.jlahougue.dndcompanion.data_weapon.data.source.remote.WeaponMixedRemoteDataSource
import com.jlahougue.dndcompanion.data_weapon.data.source.remote.subsource.WeaponDnd5eDataSource
import com.jlahougue.dndcompanion.data_weapon.data.source.remote.subsource.WeaponFirebaseDataSource

class MixedRemoteDataSource(
    private val dispatcherProvider: DispatcherProvider,
    private val firebaseDataSource: FirebaseDataSource,
    private val dnd5eDataSource: Dnd5eDataSource,
    private val open5eDataSource: Open5eDataSource
): RemoteDataSource {
    override val authDao by lazy { AuthFirebaseDataSource(firebaseDataSource.auth) }
    override val characterSheetDao by lazy { CharacterSheetFirebaseDataSource(firebaseDataSource) }
    override val characterDao by lazy { CharacterFirebaseDataSource(firebaseDataSource) }
    override val healthDao by lazy { HealthFirebaseDataSource(firebaseDataSource) }
    override val abilityDao by lazy { AbilityFirebaseDataSource(firebaseDataSource) }
    override val skillDao by lazy { SkillFirebaseDataSource(firebaseDataSource) }
    override val statsDao by lazy { StatsFirebaseDataSource(firebaseDataSource) }
    override val moneyDao by lazy { MoneyFirebaseDataSource(firebaseDataSource) }
    override val itemDao by lazy { ItemFirebaseDataSource(firebaseDataSource) }
    override val classDao by lazy {
        ClassMixedRemoteDataSource(
            ClassOpen5eDataSource(
                dispatcherProvider,
                open5eDataSource
            ),
            ClassDnd5eDataSource(
                dispatcherProvider,
                dnd5eDataSource
            )
        )
    }
    override val damageTypeDao by lazy {
        DamageTypeDnd5eDataSource(
            dispatcherProvider,
            dnd5eDataSource
        )
    }
    override val characterSpellDao by lazy { CharacterSpellFirebaseDataSource(firebaseDataSource) }
    override val spellDao by lazy {
        SpellOpen5eDataSource(
            dispatcherProvider,
            open5eDataSource
        )
    }
    override val propertyDao by lazy {
        PropertyDnd5eDataSource(
            dispatcherProvider,
            dnd5eDataSource
        )
    }
    override val weaponDao by lazy {
        WeaponMixedRemoteDataSource(
            WeaponFirebaseDataSource(firebaseDataSource),
            WeaponDnd5eDataSource(
                dispatcherProvider,
                dnd5eDataSource
            )
        )
    }
}