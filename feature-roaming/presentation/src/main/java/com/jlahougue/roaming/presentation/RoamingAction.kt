package com.jlahougue.roaming.presentation

import com.jlahougue.health_presentation.HealthEvent
import com.jlahougue.item_presentation.ItemEvent
import com.jlahougue.item_presentation.dialog.ItemDialogEvent

sealed class RoamingAction {
    data class OnHealthAction(val action: HealthEvent) : RoamingAction()
    data class OnItemAction(val action: ItemEvent) : RoamingAction()
    data class OnItemDialogAction(val action: ItemDialogEvent) : RoamingAction()
    data object OnShortRest : RoamingAction()
    data object OnLongRest : RoamingAction()
}