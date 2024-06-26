package com.jlahougue.note.data

import com.jlahougue.core_data_interface.RemoteUserDataSource
import com.jlahougue.note.domain.di.INoteModule

class NoteModule(
    remoteDataSource: RemoteUserDataSource,
    localDataSource: NoteLocalDataSource
) : INoteModule {
    override val repository: NoteRepository by lazy {
        NoteRepository(
            remoteDataSource,
            localDataSource
        )
    }
}
