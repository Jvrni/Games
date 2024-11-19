package com.games

import android.app.Application
import com.games.di.domainModule
import com.games.di.serviceModule
import com.games.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)

            // Load modules
            modules(
                uiModule,
                domainModule,
                serviceModule
            )
        }
    }
}