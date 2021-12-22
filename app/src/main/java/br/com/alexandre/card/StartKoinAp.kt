package br.com.alexandre.card

import android.app.Application
import br.com.alexandre.card.di.appModule
import br.com.alexandre.card.di.databaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class StartKoinAp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin{
            androidLogger()
            androidContext(this@StartKoinAp)
            modules(listOf(appModule, databaseModule))
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}