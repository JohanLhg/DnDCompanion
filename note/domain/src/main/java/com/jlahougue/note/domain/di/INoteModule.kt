package com.jlahougue.note.domain.di

import com.jlahougue.note.domain.repository.INoteRepository

interface INoteModule {
    val repository: INoteRepository
}