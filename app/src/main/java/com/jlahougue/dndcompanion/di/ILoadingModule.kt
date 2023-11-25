package com.jlahougue.dndcompanion.di

import com.jlahougue.dndcompanion.feature_loading_screen.domain.use_case.LoadAllFromRemote

interface ILoadingModule {
    val loadAllFromRemote: LoadAllFromRemote
}