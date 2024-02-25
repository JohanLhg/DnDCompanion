package com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider

import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.StandardTestDispatcher

class TestDispatcherProvider :
    DispatcherProvider {
    override val main: CoroutineDispatcher
        get() = StandardTestDispatcher()
    override val io: CoroutineDispatcher
        get() = StandardTestDispatcher()
    override val default: CoroutineDispatcher
        get() = StandardTestDispatcher()
    override val unconfined: CoroutineDispatcher
        get() = StandardTestDispatcher()
}