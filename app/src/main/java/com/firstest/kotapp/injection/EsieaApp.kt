package com.firstest.kotapp.injection

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class EsieaApp  : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@EsieaApp)
            modules(presentationModule, domainModule, dataModule)
        }
    }
}