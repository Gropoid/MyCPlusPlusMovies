package fr.geraud

import android.app.Application
import timber.log.Timber.*

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        plant(DebugTree())
    }
}