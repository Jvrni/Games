package com.games

import android.app.Application
import com.games.di.serviceModule
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Load modules
            modules(
                serviceModule
            )
        }
    }
}