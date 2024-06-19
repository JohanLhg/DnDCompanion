package com.jlahougue.combat_presentation

import com.jlahougue.character_spell_domain.model.SpellLevel
import com.jlahougue.character_spell_domain.model.SpellcasterView
import com.jlahougue.combat_presentation.component.TabItem
import com.jlahougue.combat_presentation.component.TabState
import com.jlahougue.item_presentation.InventoryState
import com.jlahougue.user_info_domain.model.UnitSystem
import com.jlahougue.weapon_presentation.WeaponState

data class CombatTabState(
    override val tabs: List<TabItem>,
    override val selectedTabIndex: Int,
    val unitSystem: UnitSystem = UnitSystem.METRIC,
    val weapons: WeaponState = WeaponState(),
    val inventory: InventoryState = InventoryState(),
    val spellcasterStats: SpellcasterView = SpellcasterView(),
    val spells: List<SpellLevel> = emptyList()
) : TabState(tabs, selectedTabIndex)
