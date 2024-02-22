package com.jlahougue.dndcompanion.data_spell.di

import com.jlahougue.dndcompanion.data_spell.domain.repository.ISpellRepository

interface ISpellModule {
    val repository: ISpellRepository
}