package com.adrian.githubuserlist

import android.app.Application
import com.adrian.githubuserlist.core.di.databaseModule
import com.adrian.githubuserlist.core.di.networkModule
import com.adrian.githubuserlist.core.di.repositoryModule
import com.adrian.githubuserlist.di.useCaseModule
import com.adrian.githubuserlist.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    networkModule,
                    databaseModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}