package com.gma.showdebola.config

import android.app.Application
import com.gma.infrastructure.config.infrastructureModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(
                applicationModule,
                infrastructureModule
            )
        }
    }
}