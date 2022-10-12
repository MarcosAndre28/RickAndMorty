package com.example.rickandmorty

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

// Tem que ser declarado no manifesto, usada para informa que vamos usar o Hilt no aplication
@HiltAndroidApp
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
