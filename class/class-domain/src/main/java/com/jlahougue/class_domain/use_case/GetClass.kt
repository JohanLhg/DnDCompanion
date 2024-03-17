package com.jlahougue.class_domain.use_case

import com.jlahougue.class_domain.repository.IClassRepository

class GetClass(private val repository: IClassRepository) {
    operator fun invoke(name: String) = repository.get(name)
}