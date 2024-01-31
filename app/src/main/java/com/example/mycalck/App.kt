package com.example.mycalck

import android.app.Application
import com.example.mycalck.di.networkModule
import com.example.mycalck.di.repositoryModule
import com.example.mycalck.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(networkModule, repositoryModule, viewModelModule))
        }
    }
}
