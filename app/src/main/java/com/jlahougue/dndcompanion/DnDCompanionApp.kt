package com.jlahougue.dndcompanion

import android.app.Application
import com.jlahougue.dndcompanion.di.AuthModule
import com.jlahougue.dndcompanion.di.AuthModuleImpl

class DnDCompanionApp: Application() {

    companion object {
        lateinit var authModule: AuthModule
    }

    override fun onCreate() {
        super.onCreate()
        authModule = AuthModuleImpl(this)
    }
}