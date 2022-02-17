package com.dashrath.kotlinrepositorypattern

import android.app.Application
import com.dashrath.kotlinrepositorypattern.di.networkModule
import com.dashrath.kotlinrepositorypattern.di.repositoryModule
import com.dashrath.kotlinrepositorypattern.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class KotlinDemoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeTimber()
        startKoin {
            androidContext(this@KotlinDemoApp)
            modules(listOf(networkModule, repositoryModule, viewModelModule))
        }
    }

    private fun initializeTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}