package com.jlahougue.spell_domain.di

import com.jlahougue.spell_domain.repository.ISpellRepository

interface ISpellModule {
    val repository: ISpellRepository
}