package com.asefactory.getapplicationlasttimeusage

import android.app.Application
import com.asefactory.getapplicationlasttimeusage.di.dataModule
import com.asefactory.getapplicationlasttimeusage.di.domainModule
import com.asefactory.getapplicationlasttimeusage.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                dataModule,
                domainModule,
                viewModelModule
            )
        }
    }
}