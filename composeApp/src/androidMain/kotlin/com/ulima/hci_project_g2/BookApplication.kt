package com.ulima.hci_project_g2

import android.app.Application
import com.ulima.hci_project_g2.di.initKoin
import org.koin.android.ext.koin.androidContext

class BookApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@BookApplication)
        }
    }
}