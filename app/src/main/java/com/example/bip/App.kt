package com.example.bip

import android.app.Application
import com.example.bip.di.ApplicationComponent
import com.example.bip.di.DaggerApplicationComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.factory().create(this.applicationContext)
    }

    companion object {
        lateinit var appComponent: ApplicationComponent
            private set
    }
}
