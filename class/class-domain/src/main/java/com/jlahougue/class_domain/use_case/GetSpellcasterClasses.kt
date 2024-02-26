package com.jlahougue.class_domain.use_case

import com.jlahougue.class_domain.repository.IClassRepository

class GetSpellcasterClasses(private val repository: IClassRepository) {

    suspend operator fun invoke() = repository.getSpellcasterClasses()
}