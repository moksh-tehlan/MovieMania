package com.moksh.moviemania

import android.app.Application
import di.initKoin

class MainApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}