package com.jlahougue.class_domain.use_case

data class ClassUseCases(
    val getClass: GetClass,
    val getClasses: GetClasses,
    val getClassLevels: GetClassLevels,
    val getSpellcasterClasses: GetSpellcasterClasses
)