package com.gma.showdebola.application

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        startKoin {
            androidContext(this@AppApplication)
            modules(
                appModule
            )
        }
    }

    companion object {
        lateinit var appContext: Context
    }
}