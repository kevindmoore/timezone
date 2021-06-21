package com.raywenderlich.timezone.android

import android.app.Application
import com.raywenderlich.timezone.di.initKoin
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class TimezoneApp: Application() {
    override fun onCreate() {
        super.onCreate()

        Napier.base(DebugAntilog())
        initKoin {
            androidLogger()
            androidContext(this@TimezoneApp)
        }
    }
}