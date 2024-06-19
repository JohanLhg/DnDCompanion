package com.jlahougue.roaming.domain

import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider

data class RoamingModule(
    val dispatcherProvider: DispatcherProvider
)
