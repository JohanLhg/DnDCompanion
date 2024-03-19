package com.jlahougue.class_domain.use_case

import com.jlahougue.class_domain.repository.IClassRepository
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import kotlinx.coroutines.withContext

class GetClass(
    private val dispatcherProvider: DispatcherProvider,
    private val repository: IClassRepository
) {
    suspend operator fun invoke(name: String) = withContext(dispatcherProvider.io) {
        repository.get(name)
    }
}