package com.jlahougue.core_data.source.remote

import com.jlahougue.ability_data.source.remote.AbilityFirebaseDataSource
import com.jlahougue.authentication_data.source.AuthFirebaseDataSource
import com.jlahougue.character_data.source.remote.CharacterFirebaseDataSource
import com.jlahougue.character_sheet_data.source.remote.CharacterSheetFirebaseDataSource
import com.jlahougue.character_spell_data.source.remote.CharacterSpellFirebaseDataSource
import com.jlahougue.class_data.source.remote.ClassMixedRemoteDataSource
import com.jlahougue.class_data.source.remote.subsource.ClassDnd5eDataSource
import com.jlahougue.class_data.source.remote.subsource.ClassOpen5eDataSource
import com.jlahougue.core_data_remote_instance.Dnd5eDataSource
import com.jlahougue.core_data_remote_instance.FirebaseDataSource
import com.jlahougue.core_data_remote_instance.Open5eDataSource
import com.jlahougue.core_di.RemoteDataSource
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.damage_type_data.source.remote.DamageTypeDnd5eDataSource
import com.jlahougue.health_data.source.remote.HealthFirebaseDataSource
import com.jlahougue.item_data.source.remote.ItemFirebaseDataSource
import com.jlahougue.money_data.source.remote.MoneyFirebaseDataSource
import com.jlahougue.property_data.source.remote.PropertyDnd5eDataSource
import com.jlahougue.skill_data.source.remote.SkillFirebaseDataSource
import com.jlahougue.spell_data.source.remote.SpellOpen5eDataSource
import com.jlahougue.stats_data.source.remote.StatsFirebaseDataSource
import com.jlahougue.weapon_data.source.remote.WeaponMixedRemoteDataSource
import com.jlahougue.weapon_data.source.remote.subsource.WeaponDnd5eDataSource
import com.jlahougue.weapon_data.source.remote.subsource.WeaponFirebaseDataSource

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