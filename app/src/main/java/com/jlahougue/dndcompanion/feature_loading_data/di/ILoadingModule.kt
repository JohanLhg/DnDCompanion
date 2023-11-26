package com.jlahougue.dndcompanion.feature_loading_data.di

import com.jlahougue.dndcompanion.feature_loading_data.domain.use_case.LoadAllFromRemote

interface ILoadingModule {
    val loadAllFromRemote: LoadAllFromRemote
}