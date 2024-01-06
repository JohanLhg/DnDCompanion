package com.jlahougue.dndcompanion.data_class.domain.use_case

import com.jlahougue.dndcompanion.data_class.domain.repository.IClassRepository

class GetSpellcasterClasses(
    private val repository: IClassRepository
) {

    suspend operator fun invoke() = repository.getSpellcasterClasses()
}