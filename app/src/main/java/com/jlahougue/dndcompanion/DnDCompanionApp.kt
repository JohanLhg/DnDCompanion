package com.jlahougue.dndcompanion

import android.app.Application
import com.jlahougue.dndcompanion.di.AppModule
import com.jlahougue.dndcompanion.di.AuthModule
import com.jlahougue.dndcompanion.di.IAppModule
import com.jlahougue.dndcompanion.di.IAuthModule
import com.jlahougue.dndcompanion.di.ILoadingModule
import com.jlahougue.dndcompanion.di.IRepositoriesModule
import com.jlahougue.dndcompanion.di.LoadingModule
import com.jlahougue.dndcompanion.di.RepositoriesModule

class DnDCompanionApp: Application() {

    companion object {
        lateinit var appModule: IAppModule
        lateinit var authModule: IAuthModule
        lateinit var repositoriesModule: IRepositoriesModule
        lateinit var loadingModule: ILoadingModule
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModule()
        authModule = AuthModule()
        repositoriesModule = RepositoriesModule(this, appModule.dispatcher)
        loadingModule = LoadingModule(appModule.dispatcher, repositoriesModule)
    }
}